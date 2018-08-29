package org.openmrs.isanteplus.performancedata.generator.service;

import me.tongfei.progressbar.ProgressBar;
import org.openmrs.isanteplus.performancedata.generator.predefined.PatientIdEnum;
import org.openmrs.isanteplus.performancedata.generator.util.ChunkKeeper;
import org.openmrs.isanteplus.performancedata.generator.util.IdUtil;
import org.openmrs.isanteplus.performancedata.model.Encounter;
import org.openmrs.isanteplus.performancedata.model.Entity;
import org.openmrs.isanteplus.performancedata.model.Obs;
import org.openmrs.isanteplus.performancedata.model.Patient;
import org.openmrs.isanteplus.performancedata.model.PatientIdentifier;
import org.openmrs.isanteplus.performancedata.model.Person;
import org.openmrs.isanteplus.performancedata.model.PersonAddress;
import org.openmrs.isanteplus.performancedata.model.PersonName;
import org.openmrs.isanteplus.performancedata.model.Visit;
import org.openmrs.isanteplus.performancedata.model.ClinicDataChunk;
import org.openmrs.isanteplus.performancedata.model.connection.DataManager;
import org.openmrs.isanteplus.performancedata.options.GeneratorOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeneratorService {

    @Value("${generator.chunks.patient.number}")
    private long patientChunkNumber;

    @Value("${generator.packet.size}")
    private long packetSize;

    @Value("${generator.inserts.number}")
    private long insertsNumber;

    @Inject
    private PersonGeneratorService personGeneratorService;

    @Inject
    private PatientGeneratorService patientGeneratorService;

    @Inject
    private VisitGeneratorService visitGeneratorService;

    @Inject
    private EncounterGeneratorService encounterGeneratorService;

    @Inject
    private ObsGenerationService obsGenerationService;

    @Inject
    private PersonAddressGeneratorService personAddressGeneratorService;

    @Inject
    private PersonNameGeneratorService personNameGeneratorService;

    @Inject
    private PatientIdGeneratorService patientIdGeneratorService;

    public void generateDatabase(GeneratorOptions options) throws PropertyVetoException, SQLException  {
        DataManager ins = new DataManager(options, insertsNumber, packetSize);

        ProgressBar progressBar = new ProgressBar("Generation...",
                options.getClinicNumber() * options.getPatientNumber());

        try {
            progressBar.start();
            for (long i = 0; i < options.getClinicNumber(); i++) {
                generateClinicData(options, ins, progressBar);
            }
            addPersonalData(options, ins);
            // Adds additional observations
            addObsData(ins);
        } finally {
            ins.closePool();
            progressBar.stop();
        }
    }

    private void addObsData(DataManager dataManager) throws SQLException {
        Encounter encounter = new Encounter();
        IdUtil.initObs(dataManager);

        long size = dataManager.getCount(encounter);
        ChunkKeeper chunkKeeper = new ChunkKeeper(size, insertsNumber);

        while (chunkKeeper.hasNext()) {
            List<Encounter> encounters = dataManager.fetchEncounters(encounter.getSelect(
                    chunkKeeper.getChunkSize(), chunkKeeper.getCurrent()));
            ClinicDataChunk dataChunk = new ClinicDataChunk();

            for (Encounter enc : encounters) {
                List<Obs> observations = obsGenerationService.generateLabResults(enc, enc.getDateChanged());
                dataChunk.addAllObses(observations);
                if (observations.size() >= chunkKeeper.getChunkSize()) {
                    dataChunk.insertAndFlush(dataManager);
                }
            }

            dataChunk.insertAndFlush(dataManager);
            chunkKeeper.getChunk();
        }
    }

    private void addPersonalData(GeneratorOptions options, DataManager ins) throws SQLException {
        Patient pat = new Patient();

        long size = ins.getCount(pat);
        ChunkKeeper chunkKeeper = new ChunkKeeper(size, insertsNumber);

        while (chunkKeeper.hasNext()) {
            List<Entity> entities = ins.fetchEntities(pat.getSelect(chunkKeeper.getChunkSize(), chunkKeeper.getCurrent()));

            List<PatientIdentifier> identifiers = patientIdGeneratorService.generateEntities(entities,
                    options.getStartDate(), PatientIdEnum.ECID);
            identifiers.addAll(patientIdGeneratorService.generateEntities(entities,
                    options.getStartDate(), PatientIdEnum.ISANTE_ID));
            identifiers.addAll(patientIdGeneratorService.generateEntities(entities,
                    options.getStartDate(), PatientIdEnum.ISANTE_PLUS_ID));
            identifiers.addAll(patientIdGeneratorService.generateEntities(entities,
                    options.getStartDate(), PatientIdEnum.CODE_NATIONAL));
            ins.insertEntities(new ArrayList<>(identifiers));

            List<PersonName> names = personNameGeneratorService.generateEntities(entities, options.getStartDate());
            ins.insertEntities(new ArrayList<>(names));


            List<PersonAddress> addresses = personAddressGeneratorService.generateEntities(entities, options.getStartDate());
            ins.insertEntities(new ArrayList<>(addresses));

            chunkKeeper.getChunk();
        }
    }

    private void generateClinicData(GeneratorOptions options, DataManager ins,
                                    ProgressBar progressBar) {
        ChunkKeeper chunkKeeper = new ChunkKeeper(options.getPatientNumber(), patientChunkNumber);

        while (chunkKeeper.hasNext()) {
            ClinicDataChunk dataChunk = new ClinicDataChunk();

            addPatientsDataToChunk(chunkKeeper.getChunk(), options, dataChunk);

            addVisitationDataToChunk(options, dataChunk);

            addEncounterDataToChunk(options, dataChunk);

            addObsDataToChunk(options, dataChunk);

            dataChunk.insertAll(ins);
            progressBar.stepBy(chunkKeeper.getLastChunkSize());
        }
    }

    private void addPatientsDataToChunk(long amount, GeneratorOptions options,
                                        ClinicDataChunk dataChunk) {
        List<Person> people = personGeneratorService.generateEntities(amount,
                options.getStartDate());
        dataChunk.addAllPeople(people);

        List<Patient> patients = patientGeneratorService.generateEntities(people);
        dataChunk.addAllPatients(patients);
    }

    private void addVisitationDataToChunk(GeneratorOptions options, ClinicDataChunk dataChunk) {
        for (Patient patient : dataChunk.getPatients()) {
            List<Visit> visits = visitGeneratorService.generateEntities(
                    patient, options.getVisitNumber(), patient.getDateChanged());

            dataChunk.addAllVisits(visits);
        }
    }

    private void addEncounterDataToChunk(GeneratorOptions options, ClinicDataChunk dataChunk) {
        for (Visit visit : dataChunk.getVisits()) {
            List<Encounter> encounters = encounterGeneratorService.generateEntities(
                    visit, options.getEncounterNumber(), visit.getDateChanged());

            dataChunk.addAllEncounters(encounters);
        }
    }

    private void addObsDataToChunk(GeneratorOptions options, ClinicDataChunk dataChunk) {
        for (Encounter encounter : dataChunk.getEncounters()) {
            List<Obs> observations = obsGenerationService.generateEntities(
                    encounter, options.getObsNumber(), encounter.getDateChanged());

            dataChunk.addAllObses(observations);
        }
    }
}

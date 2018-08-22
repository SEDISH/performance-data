package org.openmrs.isanteplus.performancedata.generator.service;

import me.tongfei.progressbar.ProgressBar;
import org.openmrs.isanteplus.performancedata.generator.util.ChunkKeeper;
import org.openmrs.isanteplus.performancedata.model.Encounter;
import org.openmrs.isanteplus.performancedata.model.Entity;
import org.openmrs.isanteplus.performancedata.model.Obs;
import org.openmrs.isanteplus.performancedata.model.Patient;
import org.openmrs.isanteplus.performancedata.model.Person;
import org.openmrs.isanteplus.performancedata.model.PersonAddress;
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

    public void generateDatabase(GeneratorOptions options) throws PropertyVetoException, SQLException  {
        DataManager ins = new DataManager(options, insertsNumber, packetSize);

        ProgressBar progressBar = new ProgressBar("Generation...",
                options.getClinicNumber() * options.getPatientNumber());

        try {

            progressBar.start();
            addPersonalData(options, ins, progressBar);
//            for (long i = 0; i < options.getClinicNumber(); i++) {
//                generateClinicData(options, ins, progressBar);
//            }
        } finally {
            ins.closePool();
            progressBar.stop();
        }
    }

    private void addPersonalData(GeneratorOptions options, DataManager ins,
                                    ProgressBar progressBar) throws SQLException {
        Patient pat = new Patient();
        List<Entity> entities = ins.fetchEntities(pat.getTABLE_NAME(), pat.getID_COLUMN());

        ChunkKeeper chunkKeeper = new ChunkKeeper(entities.size(), patientChunkNumber);

        while (chunkKeeper.hasNext()) {
            List<PersonAddress> addresses = personAddressGeneratorService.generateEntities(entities,
                    options.getStartDate());

            ins.insertEntities(new ArrayList<>(addresses));
            chunkKeeper.getChunk();
            progressBar.stepBy(chunkKeeper.getLastChunkSize());
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

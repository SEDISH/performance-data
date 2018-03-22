package org.openmrs.isanteplus.performancedata.generator.service;

import org.openmrs.isanteplus.performancedata.generator.util.ChunkKeeper;
import org.openmrs.isanteplus.performancedata.model.Encounter;
import org.openmrs.isanteplus.performancedata.model.Patient;
import org.openmrs.isanteplus.performancedata.model.Person;
import org.openmrs.isanteplus.performancedata.model.Visit;
import org.openmrs.isanteplus.performancedata.model.connection.ClinicDataChunk;
import org.openmrs.isanteplus.performancedata.model.connection.Inserter;
import org.openmrs.isanteplus.performancedata.options.GeneratorOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Set;

@Service
public class GeneratorService {

    @Value("${generator.chunks.patient.size}")
    private long patientChunkSize;

    @Inject
    private PersonGeneratorService personGeneratorService;

    @Inject
    private PatientGeneratorService patientGeneratorService;

    @Inject
    private VisitGeneratorService visitGeneratorService;

    @Inject
    private EncounterGeneratorService encounterGeneratorService;

    public void generateDatabase(GeneratorOptions options) throws SQLException {
        Inserter ins = new Inserter(options);

        try {
            ins.connect();

            for (long i = 0; i < options.getClinicNumber(); i++) {
                generateClinicData(options, ins);
            }
        } finally {
            ins.disconnect();
        }
    }

    private void generateClinicData(GeneratorOptions options, Inserter ins) {
        ChunkKeeper chunkKeeper = new ChunkKeeper(options.getPatientNumber(), patientChunkSize);

        while (chunkKeeper.hasNext()) {
            ClinicDataChunk dataChunk = new ClinicDataChunk();

            addPatientsDataToChunk(chunkKeeper.getChunk(), options, dataChunk);

            addVisitationDataToChunk(options, dataChunk);

            addEncounterDataToChunk(options, dataChunk);

            dataChunk.insertAll(ins);
        }
    }

    private void addPatientsDataToChunk(long amount, GeneratorOptions options,
                                        ClinicDataChunk dataChunk) {
        Set<Person> people = personGeneratorService.generateEntities(amount,
                options.getStartDate());
        dataChunk.addAllPeople(people);

        Set<Patient> patients = patientGeneratorService.generateEntities(
                options.getStartDate(), people);
        dataChunk.addAllPatients(patients);
    }

    private void addVisitationDataToChunk(GeneratorOptions options, ClinicDataChunk dataChunk) {
        for (Patient patient : dataChunk.getPatients()) {
            Set<Visit> visits = visitGeneratorService.generateEntities(
                    patient, options.getVisitNumber(), options.getStartDate());

            dataChunk.addAllVisits(visits);
        }
    }

    private void addEncounterDataToChunk(GeneratorOptions options, ClinicDataChunk dataChunk) {
        for (Visit visit : dataChunk.getVisits()) {
            Set<Encounter> encounters = encounterGeneratorService.generateEntities(
                    visit, options.getEncounterNumber(), options.getStartDate());

            dataChunk.addAllEncounters(encounters);
        }
    }
}

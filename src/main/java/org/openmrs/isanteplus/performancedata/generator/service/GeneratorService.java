package org.openmrs.isanteplus.performancedata.generator.service;

import org.openmrs.isanteplus.performancedata.generator.service.impl.PatientGeneratorService;
import org.openmrs.isanteplus.performancedata.generator.service.impl.VisitGeneratorService;
import org.openmrs.isanteplus.performancedata.generator.util.ChunkKeeper;
import org.openmrs.isanteplus.performancedata.model.AbstractEntity;
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

    @Value("${generator.chunks.visit.size}")
    private long visitChunkSize;

    @Inject
    private PatientGeneratorService patientGeneratorService;

    @Inject
    private VisitGeneratorService visitGeneratorService;

    public void generateDatabase(GeneratorOptions options) throws SQLException {
        Inserter ins = new Inserter(options);

        try {
            ins.connect();

            for (long i = 0; i < options.getClinics(); i++) {
                generateClinicData(options, ins);
            }
        } finally {
            ins.disconnect();
        }
    }

    private void generateClinicData(GeneratorOptions options, Inserter ins) {
        ChunkKeeper chunkKeeper = new ChunkKeeper(options.getPatients(), patientChunkSize);
        while (chunkKeeper.hasNext()) {
            Set<AbstractEntity> patients = patientGeneratorService.generateEntities(
                    chunkKeeper.getChunk(), options.getStartDate(), ins);
            ins.insertEntities(patients);

            generateVisitationData(patients, options, ins);
        }
    }

    private void generateVisitationData(Set<AbstractEntity> patients, GeneratorOptions options,
                                       Inserter ins) {
        for (AbstractEntity entity : patients) {
            ChunkKeeper chunkKeeper = new ChunkKeeper(options.getVisits(), visitChunkSize);
            while (chunkKeeper.hasNext()) {
                Set<AbstractEntity> visits = visitGeneratorService.generateEntities(
                        entity, chunkKeeper.getChunk(), options.getStartDate(), ins);
                ins.insertEntities(visits);
            }
        }
    }
}

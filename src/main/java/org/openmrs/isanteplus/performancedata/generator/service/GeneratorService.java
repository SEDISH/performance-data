package org.openmrs.isanteplus.performancedata.generator.service;

import org.openmrs.isanteplus.performancedata.generator.service.impl.PatientGeneratorService;
import org.openmrs.isanteplus.performancedata.generator.service.impl.VisitGeneratorService;
import org.openmrs.isanteplus.performancedata.generator.util.ChunkKeeper;
import org.openmrs.isanteplus.performancedata.model.AbstractEntity;
import org.openmrs.isanteplus.performancedata.model.connection.ClinicDataChunk;
import org.openmrs.isanteplus.performancedata.model.connection.Inserter;
import org.openmrs.isanteplus.performancedata.options.GeneratorOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.sql.SQLException;

@Service
public class GeneratorService {

    @Value("${generator.chunks.patient.size}")
    private long patientChunkSize;

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
            ClinicDataChunk dataChunk = new ClinicDataChunk();
            dataChunk = patientGeneratorService.generateEntities(
                    chunkKeeper.getChunk(), options.getStartDate(), dataChunk);

            dataChunk = generateVisitationData(options, dataChunk);
            dataChunk.insertAll(ins);
        }
    }

    private ClinicDataChunk generateVisitationData(GeneratorOptions options,
                                        ClinicDataChunk dataChunk) {
        for (AbstractEntity entity : dataChunk.getPatients()) {
            dataChunk = visitGeneratorService.generateEntities(
                    entity, options.getVisits(), options.getStartDate(), dataChunk);
        }

        return dataChunk;
    }
}

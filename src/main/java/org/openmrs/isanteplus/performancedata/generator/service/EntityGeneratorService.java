package org.openmrs.isanteplus.performancedata.generator.service;

import org.openmrs.isanteplus.performancedata.model.connection.ClinicDataChunk;

import java.time.LocalDateTime;

public interface EntityGeneratorService {

    ClinicDataChunk generateEntities(long amount, LocalDateTime startDate,
                                         ClinicDataChunk dataChunk);
}

package org.openmrs.isanteplus.performancedata.generator.service;

import org.openmrs.isanteplus.performancedata.model.AbstractEntity;
import org.openmrs.isanteplus.performancedata.model.connection.ClinicDataChunk;

import java.time.LocalDateTime;


public interface ChildEntityGeneratorService {

    ClinicDataChunk generateEntities(AbstractEntity parent, long amount,
                                         LocalDateTime startDate, ClinicDataChunk dataChunk);
}

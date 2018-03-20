package org.openmrs.isanteplus.performancedata.generator.service;

import org.openmrs.isanteplus.performancedata.model.AbstractEntity;
import org.openmrs.isanteplus.performancedata.model.connection.Inserter;

import java.time.LocalDateTime;
import java.util.Set;

public interface ChildEntityGeneratorService {

    Set<AbstractEntity> generateEntities(AbstractEntity parent, long amount,
                                         LocalDateTime startDate, Inserter ins);
}

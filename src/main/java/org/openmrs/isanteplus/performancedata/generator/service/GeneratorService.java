package org.openmrs.isanteplus.performancedata.generator.service;

import org.openmrs.isanteplus.performancedata.generator.service.impl.PatientGeneratorService;
import org.openmrs.isanteplus.performancedata.model.AbstractEntity;
import org.openmrs.isanteplus.performancedata.model.connection.Inserter;
import org.openmrs.isanteplus.performancedata.options.GeneratorOptions;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Set;

@Service
public class GeneratorService {

    @Inject
    private PatientGeneratorService patientService;

    public void genereteDatabase(GeneratorOptions options) throws SQLException {

        Inserter ins = new Inserter(options);
        ins.connect();

        try {
            Set<AbstractEntity> patients = patientService.generateEntities(options.getPatients(),
                    options.getStartDate(), ins);
            ins.insertEntities(patients);
        } finally {
            ins.disconnect();
        }
    }

}

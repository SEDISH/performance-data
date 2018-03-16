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
    private PatientGeneratorService patientGeneratorService;

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
        Set<AbstractEntity> patients = patientGeneratorService.generateEntities(
                options.getPatients(), options.getStartDate(), ins);
        ins.insertEntities(patients);
    }
}

package org.openmrs.isanteplus.performancedata.generator.service;

import org.openmrs.isanteplus.performancedata.generator.predefined.UserEnum;
import org.openmrs.isanteplus.performancedata.generator.util.ParamData;
import org.openmrs.isanteplus.performancedata.model.Patient;
import org.openmrs.isanteplus.performancedata.model.Person;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientGeneratorService {

    public List<Patient> generateEntities(List<Person> people) {

        List<Patient> patients = new ArrayList<>();

        for (Person person : people) {
            patients.add(generatePatient(person.getId(), person.getDateChanged()));
        }

        return patients;
    }

    private Patient generatePatient(long id, LocalDateTime startDate) {
        ParamData changeInfo = new ParamData(startDate);
        ParamData voidInfo = new ParamData();

        return Patient.builder()
                .id(id)
                .creator(UserEnum.ADMIN.getId())
                .dateCreated(startDate)
                .changedBy(changeInfo.getParamBy())
                .dateChanged(changeInfo.getDateParam())
                .voided(voidInfo.getParam())
                .voidedBy(voidInfo.getParamBy())
                .dateVoided(voidInfo.getDateParam())
                .voidReason(voidInfo.getParamReason())
                .build();
    }
}

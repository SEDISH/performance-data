package org.openmrs.isanteplus.performancedata.generator.service;

import org.openmrs.isanteplus.performancedata.generator.predefined.UserEnum;
import org.openmrs.isanteplus.performancedata.generator.util.ParamData;
import org.openmrs.isanteplus.performancedata.generator.util.RandUtil;
import org.openmrs.isanteplus.performancedata.model.Patient;
import org.openmrs.isanteplus.performancedata.model.Person;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PatientGeneratorService {

    public Set<Patient> generateEntities(LocalDateTime startDate, Set<Person> people) {

        Set<Patient> patients = new HashSet<>();

        for (Person person : people) {
            patients.add(generatePatient(person.getId(), startDate));
        }

        return patients;
    }

    private Patient generatePatient(long id, LocalDateTime startDate) {
        ParamData changeInfo = new ParamData(startDate);
        LocalDateTime lastEventDate = Optional.ofNullable(changeInfo.getDateParam())
                .orElse(startDate);
        ParamData voidInfo = new ParamData(lastEventDate);

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
                .allergyStatus(RandUtil.getString())
                .build();
    }
}

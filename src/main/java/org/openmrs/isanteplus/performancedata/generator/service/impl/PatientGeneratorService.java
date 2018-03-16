package org.openmrs.isanteplus.performancedata.generator.service.impl;

import org.openmrs.isanteplus.performancedata.generator.service.EntityGeneratorService;
import org.openmrs.isanteplus.performancedata.generator.predefined.UserEnum;
import org.openmrs.isanteplus.performancedata.generator.util.*;
import org.openmrs.isanteplus.performancedata.model.AbstractEntity;
import org.openmrs.isanteplus.performancedata.model.Patient;
import org.openmrs.isanteplus.performancedata.model.connection.Inserter;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PatientGeneratorService implements EntityGeneratorService {

    @Inject
    private PersonGeneratorService personService;

    @Override
    public Set<AbstractEntity> generateEntities(long amount, LocalDateTime startDate, Inserter ins) {
        Set<Patient> patients = new HashSet<>();

        Set<AbstractEntity> persons = personService.generateEntities(amount, startDate, ins);
        ins.insertEntities(new HashSet<>(persons));

        for (AbstractEntity person : persons) {
            patients.add(generatePatient(person.getId(), startDate));
        }

        return new HashSet<>(patients);
    }

    private Patient generatePatient(long id, LocalDateTime startDate) {
        ParamData changeInfo = new ParamData(startDate);
        LocalDateTime lastEventDate = Optional.ofNullable(changeInfo.getDateParam())
                .orElse(startDate);
        ParamData voidInfo = new ParamData(lastEventDate);

        return Patient.builder()
                .id(id)
                .creator(UserEnum.ADMIN_ID.getId())
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

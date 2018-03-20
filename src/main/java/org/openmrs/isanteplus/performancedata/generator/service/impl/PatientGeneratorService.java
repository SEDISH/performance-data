package org.openmrs.isanteplus.performancedata.generator.service.impl;

import org.openmrs.isanteplus.performancedata.generator.service.EntityGeneratorService;
import org.openmrs.isanteplus.performancedata.generator.predefined.UserEnum;
import org.openmrs.isanteplus.performancedata.generator.util.ParamData;
import org.openmrs.isanteplus.performancedata.generator.util.RandUtil;
import org.openmrs.isanteplus.performancedata.model.AbstractEntity;
import org.openmrs.isanteplus.performancedata.model.Patient;
import org.openmrs.isanteplus.performancedata.model.connection.ClinicDataChunk;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PatientGeneratorService implements EntityGeneratorService {

    @Inject
    private PersonGeneratorService personService;

    @Override
    public ClinicDataChunk generateEntities(long amount, LocalDateTime startDate,
                                                ClinicDataChunk dataChunk) {

        dataChunk = personService.generateEntities(amount, startDate, dataChunk);

        for (AbstractEntity person : dataChunk.getPersons()) {
            dataChunk.addPatient(generatePatient(person.getId(), startDate));
        }

        return dataChunk;
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

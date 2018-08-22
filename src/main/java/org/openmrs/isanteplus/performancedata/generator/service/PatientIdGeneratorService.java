package org.openmrs.isanteplus.performancedata.generator.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.openmrs.isanteplus.performancedata.generator.predefined.LocationEnum;
import org.openmrs.isanteplus.performancedata.generator.predefined.PatientIdEnum;
import org.openmrs.isanteplus.performancedata.generator.predefined.UserEnum;
import org.openmrs.isanteplus.performancedata.generator.util.IdUtil;
import org.openmrs.isanteplus.performancedata.generator.util.ParamData;
import org.openmrs.isanteplus.performancedata.model.Entity;
import org.openmrs.isanteplus.performancedata.model.PatientIdentifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class PatientIdGeneratorService {

    public List<PatientIdentifier> generateEntities(List<Entity> entities, LocalDateTime startDate, PatientIdEnum idType) {
        List<PatientIdentifier> addresses = new ArrayList<>();

        for (Entity entity: entities) {
            addresses.add(generatePatientIdentifier(IdUtil.getpatientIdentifierId(), entity.getId(), startDate, idType));
        }

        return addresses;
    }

    private PatientIdentifier generatePatientIdentifier(long id, long patientId, LocalDateTime startDate,
                                                        PatientIdEnum idType) {
        ParamData changeInfo = new ParamData(startDate);
        LocalDateTime lastEventDate = Optional.ofNullable(changeInfo.getDateParam())
                .orElse(startDate);

        ParamData voidInfo = new ParamData(lastEventDate);
        RandomStringUtils.randomAlphanumeric(60);
        return PatientIdentifier.builder()
                .id(id)
                .patientId(patientId)
                .identifier(idType.generateIdentifier())
                .identifierType(idType.getId())
                .preferred(1L)
                .locationId(LocationEnum.OUTPATIENT_CLINIC.getId())
                .creator(UserEnum.ADMIN.getId())
                .dateCreated(startDate)
                .dateChanged(changeInfo.getDateParam())
                .changedBy(changeInfo.getParamBy())
                .voided(voidInfo.getParam())
                .voidedBy(voidInfo.getParamBy())
                .dateVoided(voidInfo.getDateParam())
                .voidReason(voidInfo.getParamReason())
                .uuid(UUID.randomUUID().toString())
                .build();
    }

}

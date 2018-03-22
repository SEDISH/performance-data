package org.openmrs.isanteplus.performancedata.generator.service;

import org.openmrs.isanteplus.performancedata.generator.predefined.EncounterTypeEnum;
import org.openmrs.isanteplus.performancedata.generator.predefined.FormEnum;
import org.openmrs.isanteplus.performancedata.generator.predefined.LocationEnum;
import org.openmrs.isanteplus.performancedata.generator.predefined.UserEnum;
import org.openmrs.isanteplus.performancedata.generator.util.IdUtil;
import org.openmrs.isanteplus.performancedata.generator.util.ParamData;
import org.openmrs.isanteplus.performancedata.generator.util.RandUtil;
import org.openmrs.isanteplus.performancedata.model.Encounter;
import org.openmrs.isanteplus.performancedata.model.Visit;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class EncounterGeneratorService {

    public Set<Encounter> generateEntities(Visit parent, long amount, LocalDateTime startDate) {
        Set<Encounter> encounters = new HashSet<>();
        for (long i = 0; i < amount; i++) {
            encounters.add(generateEncounter(parent, startDate));
        }

        return encounters;
    }

    private Encounter generateEncounter(Visit parent, LocalDateTime startDate) {
        ParamData changeInfo = new ParamData(startDate);
        LocalDateTime lastEventDate = Optional.ofNullable(changeInfo.getDateParam())
                .orElse(startDate);
        ParamData voidInfo = new ParamData(lastEventDate);

        return Encounter.builder()
                .id(IdUtil.getEncounterId())
                .encounterType(EncounterTypeEnum.VISIT_NOTE.getId())
                .patientId(parent.getPatientId())
                .locationId(LocationEnum.OUTPATIENT_CLINIC.getId())
                .formId(FormEnum.VISIT_NOTE.getId())
                .encounterDatetime(RandUtil.getDateFromStartToNextDay(startDate))
                .creator(UserEnum.ADMIN.getId())
                .dateCreated(startDate)
                .voided(voidInfo.getParam())
                .voidedBy(voidInfo.getParamBy())
                .dateVoided(voidInfo.getDateParam())
                .voidReason(voidInfo.getParamReason())
                .changedBy(changeInfo.getParamBy())
                .dateChanged(changeInfo.getDateParam())
                .visitId(parent.getId())
                .uuid(UUID.randomUUID().toString())
                .build();
    }
}
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EncounterGeneratorService {

    public List<Encounter> generateEntities(Visit parent, long amount, LocalDateTime startDate) {
        List<Encounter> encounters = new ArrayList<>();
        for (long i = 0; i < amount; i++) {
            encounters.add(generateEncounter(parent, startDate));
        }

        return encounters;
    }

    private Encounter generateEncounter(Visit parent, LocalDateTime startDate) {
        ParamData changeInfo = new ParamData(startDate);
        ParamData voidInfo = new ParamData();

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

package org.openmrs.isanteplus.performancedata.generator.service;

import org.openmrs.isanteplus.performancedata.generator.predefined.ConceptEnum;
import org.openmrs.isanteplus.performancedata.generator.predefined.LocationEnum;
import org.openmrs.isanteplus.performancedata.generator.predefined.UserEnum;
import org.openmrs.isanteplus.performancedata.generator.predefined.VisitTypeEnum;
import org.openmrs.isanteplus.performancedata.generator.util.IdUtil;
import org.openmrs.isanteplus.performancedata.generator.util.ParamData;
import org.openmrs.isanteplus.performancedata.generator.util.RandUtil;
import org.openmrs.isanteplus.performancedata.model.Patient;
import org.openmrs.isanteplus.performancedata.model.Visit;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VisitGeneratorService {

    public List<Visit> generateEntities(Patient parent, long amount, LocalDateTime startDate) {
        List<Visit> visits = new ArrayList<>();

        for (long i = 0; i < amount; i++) {
            visits.add(generateVisit(parent.getId(), startDate));
        }

        return visits;
    }

    private Visit generateVisit(long parentId, LocalDateTime startDate) {
        ParamData changeInfo = new ParamData(startDate);
        ParamData voidInfo = new ParamData();

        return Visit.builder()
                .id(IdUtil.getVisitId())
                .patientId(parentId)
                .visitTypeId(VisitTypeEnum.FACILITY_VISIT.getId())
                .dateStarted(startDate)
                .dateStopped(RandUtil.getDateFromStartToNextDay(startDate))
                .indicationConceptId(ConceptEnum.REBOUND.getId())
                .locationId(LocationEnum.CSL_DE_CABARET.getId())
                .creator(UserEnum.ADMIN.getId())
                .dateCreated(startDate)
                .changedBy(changeInfo.getParamBy())
                .dateChanged(changeInfo.getDateParam())
                .voided(voidInfo.getParam())
                .voidedBy(voidInfo.getParamBy())
                .dateVoided(voidInfo.getDateParam())
                .voidReason(voidInfo.getParamReason())
                .uuid(UUID.randomUUID().toString())
                .build();
    }
}

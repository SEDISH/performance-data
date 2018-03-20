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
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class VisitGeneratorService {

    public Set<Visit> generateEntities(Patient parent, long amount, LocalDateTime startDate) {
        Set<Visit> visits = new HashSet<>();

        for (long i = 0; i < amount; i++) {
            visits.add(generateVisit(parent.getId(), startDate));
        }

        return visits;
    }

    private Visit generateVisit(long parentId, LocalDateTime startDate) {
        ParamData changeInfo = new ParamData(startDate);
        LocalDateTime lastEventDate = Optional.ofNullable(changeInfo.getDateParam())
                .orElse(startDate);
        ParamData voidInfo = new ParamData(lastEventDate);

        return Visit.builder()
                .id(IdUtil.getVisitId())
                .patientId(parentId)
                .visitTypeId(VisitTypeEnum.FACILITY_VISIT.getId())
                .dateStarted(startDate)
                .dateStopped(RandUtil.getDateFromStartToNextDay(startDate))
                .indicationConceptId(ConceptEnum.REBOUND.getId())
                .locationId(LocationEnum.OUTPATIENT_CLINIC.getId())
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

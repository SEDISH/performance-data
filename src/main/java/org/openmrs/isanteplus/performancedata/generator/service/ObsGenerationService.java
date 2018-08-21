package org.openmrs.isanteplus.performancedata.generator.service;

import org.openmrs.isanteplus.performancedata.generator.predefined.ConceptEnum;
import org.openmrs.isanteplus.performancedata.generator.predefined.UserEnum;
import org.openmrs.isanteplus.performancedata.generator.util.IdUtil;
import org.openmrs.isanteplus.performancedata.generator.util.ParamData;
import org.openmrs.isanteplus.performancedata.model.Encounter;
import org.openmrs.isanteplus.performancedata.model.Obs;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ObsGenerationService {

    public List<Obs> generateEntities(Encounter encounter, long amount,
                                      LocalDateTime startDate) {
        List<Obs> observations = new ArrayList<>();
        for (long i = 0; i < amount; i++) {
            observations.add(generateObs(encounter, startDate));
        }

        return observations;
    }

    private Obs generateObs(Encounter parent, LocalDateTime date) {

        ParamData voidInfo = new ParamData(date);

        return Obs.builder()
                .id(IdUtil.getObsId())
                .personId(parent.getPatientId()) // it is allowed, because patient and person id
                // have to be the same
                .conceptId(ConceptEnum.REBOUND.getId())
                .encounterId(parent.getId())
                .orderId(null)
                .obsDatetime(date)
                .locationId(parent.getLocationId())
                .obsGroupId(null)
                .accessionNumber(UUID.randomUUID().toString())
                .valueGroupId(null)
                .valueBoolean(null)
                .valueCoded(null)
                .valueCodedNameId(null)
                .valueDrug(null)
                .valueDatetime(null)
                .valueNumeric(null)
                .valueModifier(null)
                .valueText(null)
                .valueComplex(null)
                .comments(UUID.randomUUID().toString())
                .creator(UserEnum.ADMIN.getId())
                .dateCreated(date)
                .voided(voidInfo.getParam())
                .voidedBy(voidInfo.getParamBy())
                .dateVoided(voidInfo.getDateParam())
                .voidReason(voidInfo.getParamReason())
                .uuid(UUID.randomUUID().toString())
                .previousVersion(null)
                .formNamespaceAndPath(UUID.randomUUID().toString())
                .build();
    }
}

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

import static org.openmrs.isanteplus.performancedata.generator.predefined.ConceptEnum.AGE_UNIT;
import static org.openmrs.isanteplus.performancedata.generator.predefined.ConceptEnum.AGE_WHEN_TESTED;
import static org.openmrs.isanteplus.performancedata.generator.predefined.ConceptEnum.LABORATORY_RESULTS;
import static org.openmrs.isanteplus.performancedata.generator.predefined.ConceptEnum.RAPID_TEST_FOR_HIV;
import static org.openmrs.isanteplus.performancedata.generator.predefined.ConceptEnum.TEST_NAME;

@Service
public class ObsGenerationService {

    public List<Obs> generateEntities(Encounter encounter, long amount,
                                      LocalDateTime startDate) {
        List<Obs> observations = new ArrayList<>();
        for (long i = 0; i < amount; i++) {
            observations.add(generateNewObs(encounter, startDate));
        }

        return observations;
    }

    private Obs generateNewObs(Encounter parent, LocalDateTime date) {
        ParamData voidInfo = new ParamData();
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
    
    public List<Obs> generateLabResults(Encounter encounter, LocalDateTime startDate) {
        List<Obs> observations = new ArrayList<>();
        Obs mainObs = getObs(encounter, startDate, LABORATORY_RESULTS, null);
        observations.add(mainObs);
        
        observations.add(getObs(encounter, startDate, TEST_NAME, mainObs.getId()));
        observations.add(getObs(encounter, startDate, AGE_WHEN_TESTED, mainObs.getId()));
        observations.add(getObs(encounter, startDate, AGE_UNIT, mainObs.getId()));
        observations.add(getObs(encounter, startDate, RAPID_TEST_FOR_HIV, mainObs.getId()));
        

        return observations;
    }

    private Obs getObs(Encounter parent, LocalDateTime date, ConceptEnum type, Long obsGroupId) {
        Obs obs = generateNewObs(parent, date);
        switch(type) {
            case LABORATORY_RESULTS:
                obs.setConceptId(LABORATORY_RESULTS.getId());
                obs.setObsGroupId(null);
                return obs;
            case TEST_NAME:
                obs.setConceptId(TEST_NAME.getId());
                obs.setObsGroupId(obsGroupId);
                obs.setValueCoded(163722L);
                return obs;
            case AGE_WHEN_TESTED:
                obs.setConceptId(AGE_WHEN_TESTED.getId());
                obs.setObsGroupId(obsGroupId);
                obs.setValueNumeric(1.0);
                return obs;
            case AGE_UNIT:
                obs.setConceptId(AGE_UNIT.getId());
                obs.setObsGroupId(obsGroupId);
                obs.setValueCoded(1072L);
                return obs;
            case RAPID_TEST_FOR_HIV:
                obs.setConceptId(RAPID_TEST_FOR_HIV.getId());
                obs.setObsGroupId(obsGroupId);
                return obs;
            default:
                return obs;
        }
    }

}

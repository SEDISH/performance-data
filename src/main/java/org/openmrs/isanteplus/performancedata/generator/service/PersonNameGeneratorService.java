package org.openmrs.isanteplus.performancedata.generator.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.openmrs.isanteplus.performancedata.generator.predefined.UserEnum;
import org.openmrs.isanteplus.performancedata.generator.util.IdUtil;
import org.openmrs.isanteplus.performancedata.generator.util.ParamData;
import org.openmrs.isanteplus.performancedata.model.Entity;
import org.openmrs.isanteplus.performancedata.model.PersonName;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class PersonNameGeneratorService {

    public List<PersonName> generateEntities(List<Entity> entities, LocalDateTime startDate) {
        List<PersonName> identifiers = new ArrayList<>();

        for (Entity entity: entities) {
            identifiers.add(generatePersonName(IdUtil.getPersonNameId(), entity.getId(), startDate));
        }

        return identifiers;
    }

    private PersonName generatePersonName(long id, long personId, LocalDateTime startDate) {
        ParamData changeInfo = new ParamData(startDate);
        ParamData voidInfo = new ParamData();
        RandomStringUtils.randomAlphanumeric(60);

        return PersonName.builder()
                .id(id)
                .preferred(1L)
                .personId(personId)
                .prefix(null)
                .givenName(RandomStringUtils.randomAlphabetic(8))
                .middleName(null)
                .familyNamePrefix(null)
                .familyName(RandomStringUtils.randomAlphabetic(8))
                .familyName2(null)
                .familyNameSuffix(null)
                .degree(null)
                .creator(UserEnum.ADMIN.getId())
                .dateCreated(startDate)
                .voided(voidInfo.getParam())
                .voidedBy(voidInfo.getParamBy())
                .dateVoided(voidInfo.getDateParam())
                .voidReason(voidInfo.getParamReason())
                .changedBy(changeInfo.getParamBy())
                .dateChanged(changeInfo.getDateParam())
                .uuid(UUID.randomUUID().toString())
                .build();
    }

}

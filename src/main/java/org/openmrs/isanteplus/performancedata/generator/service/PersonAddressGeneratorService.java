package org.openmrs.isanteplus.performancedata.generator.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.openmrs.isanteplus.performancedata.generator.predefined.UserEnum;
import org.openmrs.isanteplus.performancedata.generator.util.AddressConstants;
import org.openmrs.isanteplus.performancedata.generator.util.IdUtil;
import org.openmrs.isanteplus.performancedata.generator.util.ParamData;
import org.openmrs.isanteplus.performancedata.model.Entity;
import org.openmrs.isanteplus.performancedata.model.PersonAddress;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class PersonAddressGeneratorService {

    public List<PersonAddress> generateEntities(List<Entity> entities, LocalDateTime startDate) {
        List<PersonAddress> addresses = new ArrayList<>();

        for (Entity entity: entities) {
            addresses.add(generatePersonAddress(IdUtil.getPersonAddressId(), entity.getId(), startDate));
        }

        return addresses;
    }

    private PersonAddress generatePersonAddress(long id, long personId, LocalDateTime startDate) {
        ParamData changeInfo = new ParamData(startDate);
        LocalDateTime lastEventDate = Optional.ofNullable(changeInfo.getDateParam())
                .orElse(startDate);

        ParamData voidInfo = new ParamData(lastEventDate);
        RandomStringUtils.randomAlphanumeric(60);
        return PersonAddress.builder()
                .id(id)
                .personId(personId)
                .preferred(1L)
                .address1(AddressConstants.ADDRESS1)
                .address2(AddressConstants.ADDRESS2)
                .cityVillage(AddressConstants.CITY_VILLAGE)
                .stateProvince(AddressConstants.STATE_PROVIENCE)
                .postalCode(null)
                .cityVillage(AddressConstants.COUNTRY)
                .latitude(null)
                .longitude(null)
                .startDate(null)
                .endDate(null)
                .creator(UserEnum.ADMIN.getId())
                .dateCreated(startDate)
                .voided(voidInfo.getParam())
                .voidedBy(voidInfo.getParamBy())
                .dateVoided(voidInfo.getDateParam())
                .voidReason(voidInfo.getParamReason())
                .countyDistrict(null)
                .address3(null)
                .address4(null)
                .address5(null)
                .address6(null)
                .dateChanged(changeInfo.getDateParam())
                .changedBy(changeInfo.getParamBy())
                .uuid(UUID.randomUUID().toString())
                .build();
    }

}

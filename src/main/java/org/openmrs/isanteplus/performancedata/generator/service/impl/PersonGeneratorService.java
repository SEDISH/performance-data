package org.openmrs.isanteplus.performancedata.generator.service.impl;

import org.openmrs.isanteplus.performancedata.generator.predefined.ConceptEnum;
import org.openmrs.isanteplus.performancedata.generator.predefined.UserEnum;
import org.openmrs.isanteplus.performancedata.generator.service.EntityGeneratorService;
import org.openmrs.isanteplus.performancedata.generator.util.IdUtil;
import org.openmrs.isanteplus.performancedata.generator.util.RandUtil;
import org.openmrs.isanteplus.performancedata.generator.util.ParamData;
import org.openmrs.isanteplus.performancedata.model.Person;
import org.openmrs.isanteplus.performancedata.model.connection.ClinicDataChunk;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonGeneratorService implements EntityGeneratorService {

    @Override
    public ClinicDataChunk generateEntities(long amount, LocalDateTime startDate,
                                            ClinicDataChunk dataChunk) {
        for (long i = 0; i < amount; i++) {
            dataChunk.addPerson(generatePerson(IdUtil.getPersonId(), ConceptEnum.GUARDING.getId(),
                    startDate));
        }

        return dataChunk;
    }

    private Person generatePerson(long id, long conceptId, LocalDateTime startDate) {
        ParamData changeInfo = new ParamData(startDate);
        LocalDateTime lastEventDate = Optional.ofNullable(changeInfo.getDateParam())
                .orElse(startDate);

        ParamData voidInfo = new ParamData(lastEventDate);

        return Person.builder()
                .id(id)
                .gender(RandUtil.getString())
                .birthdate(startDate.toLocalDate())
                .birthdateEstimated(0L)
                .dead(0L)
                .deathDate(null)
                .causeOfDeath(conceptId)
                .creator(UserEnum.ADMIN.getId())
                .dateCreated(startDate)
                .changedBy(changeInfo.getParamBy())
                .dateChanged(changeInfo.getDateParam())
                .voided(voidInfo.getParam())
                .voidedBy(voidInfo.getParamBy())
                .dateVoided(voidInfo.getDateParam())
                .voidReason(voidInfo.getParamReason())
                .uuid(UUID.randomUUID().toString())
                .deathdateEstimated(0L)
                .birthtime(LocalTime.now())
                .build();
    }
}

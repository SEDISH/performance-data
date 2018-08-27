package org.openmrs.isanteplus.performancedata.generator.service;

import org.openmrs.isanteplus.performancedata.generator.predefined.ConceptEnum;
import org.openmrs.isanteplus.performancedata.generator.predefined.UserEnum;
import org.openmrs.isanteplus.performancedata.generator.util.IdUtil;
import org.openmrs.isanteplus.performancedata.generator.util.RandUtil;
import org.openmrs.isanteplus.performancedata.generator.util.ParamData;
import org.openmrs.isanteplus.performancedata.model.Person;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PersonGeneratorService {

    public List<Person> generateEntities(long amount, LocalDateTime startDate) {
        List<Person> people = new ArrayList<>();

        for (long i = 0; i < amount; i++) {
            people.add(generatePerson(IdUtil.getPersonId(), ConceptEnum.GUARDING.getId(),
                    startDate));
        }

        return people;
    }

    private Person generatePerson(long id, long conceptId, LocalDateTime startDate) {
        ParamData changeInfo = new ParamData(startDate);
        ParamData voidInfo = new ParamData();

        return Person.builder()
                .id(id)
                .gender(RandUtil.getGender())
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

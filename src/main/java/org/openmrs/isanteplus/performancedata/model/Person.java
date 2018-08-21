package org.openmrs.isanteplus.performancedata.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class Person extends Entity {

    protected final String TABLE_NAME = "person";
    protected final String ID_COLUMN = "person_id";
    protected final String PREPARED_SQL = "insert into " + TABLE_NAME
            + " (person_id, gender, birthdate, birthdate_estimated, dead, death_date, " +
            "cause_of_death, creator, date_created, changed_by, date_changed, voided, voided_by, " +
            "date_voided, void_reason, uuid, deathdate_estimated, birthtime)"
            + " values (:id, :gender, :birthdate, :birthdateEstimated, :dead, :deathDate, " +
            ":causeOfDeath, :creator, :dateCreated, :changedBy, :dateChanged, :voided, :voidedBy, " +
            ":dateVoided, :voidReason, :uuid, :deathdateEstimated, :birthtime)";

    private String gender;

    private LocalDate birthdate;

    private long birthdateEstimated;

    private long dead;

    private LocalDateTime deathDate;

    private long causeOfDeath;

    private long creator;

    private LocalDateTime dateCreated;

    private long changedBy;

    private LocalDateTime dateChanged;

    private long voided;

    private long voidedBy;

    private LocalDateTime dateVoided;

    private String voidReason;

    private String uuid;

    private long deathdateEstimated;

    private LocalTime birthtime;

    @Builder
    public Person(long id, String gender, LocalDate birthdate, long birthdateEstimated, long dead,
                  LocalDateTime deathDate, long causeOfDeath, long creator,
                  LocalDateTime dateCreated, long changedBy, LocalDateTime dateChanged, long voided,
                  long voidedBy, LocalDateTime dateVoided, String voidReason, String uuid,
                  long deathdateEstimated, LocalTime birthtime) {
        super(id);
        this.gender = gender;
        this.birthdate = birthdate;
        this.birthdateEstimated = birthdateEstimated;
        this.dead = dead;
        this.deathDate = deathDate;
        this.causeOfDeath = causeOfDeath;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.changedBy = changedBy;
        this.dateChanged = dateChanged;
        this.voided = voided;
        this.voidedBy = voidedBy;
        this.dateVoided = dateVoided;
        this.voidReason = voidReason;
        this.uuid = uuid;
        this.deathdateEstimated = deathdateEstimated;
        this.birthtime = birthtime;
    }
}

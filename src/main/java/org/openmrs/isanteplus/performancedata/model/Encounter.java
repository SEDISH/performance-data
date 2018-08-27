package org.openmrs.isanteplus.performancedata.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Encounter extends Entity {

    protected final String TABLE_NAME = "encounter";
    protected final String ID_COLUMN = "encounter_id";
    protected final String PREPARED_SQL = "insert into " + TABLE_NAME
            + " (encounter_id, encounter_type, patient_id, location_id, form_id, " +
            "encounter_datetime, creator, date_created, voided, voided_by, date_voided, " +
            "void_reason, changed_by, date_changed, visit_id, uuid)"
            + " values (:id, :encounterType, :patientId, :locationId, :formId, " +
            ":encounterDatetime, :creator, :dateCreated, :voided, :voidedBy, :dateVoided, " +
            ":voidReason, :changedBy, :dateChanged, :visitId, :uuid)";

    private long encounterType;

    private long patientId;

    private long locationId;

    private long formId;

    private LocalDateTime encounterDatetime;

    private long creator;

    private LocalDateTime dateCreated;

    private Long voided;

    private Long voidedBy;

    private LocalDateTime dateVoided;

    private String voidReason;

    private Long changedBy;

    private LocalDateTime dateChanged;

    private long visitId;
    
    private String uuid;

    @Builder
    public Encounter(long id, long encounterType, long patientId,
                     long locationId, long formId, LocalDateTime encounterDatetime,
                     long creator, LocalDateTime dateCreated, long voided, long voidedBy,
                     LocalDateTime dateVoided, String voidReason, long changedBy,
                     LocalDateTime dateChanged, long visitId, String uuid) {
        super(id);
        this.encounterType = encounterType;
        this.patientId = patientId;
        this.locationId = locationId;
        this.formId = formId;
        this.encounterDatetime = encounterDatetime;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.voided = voided;
        this.voidedBy = voidedBy;
        this.dateVoided = dateVoided;
        this.voidReason = voidReason;
        this.changedBy = changedBy;
        this.dateChanged = dateChanged;
        this.visitId = visitId;
        this.uuid = uuid;
    }
}

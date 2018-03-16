package org.openmrs.isanteplus.performancedata.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Encounter extends AbstractEntity {

    private static final String PREPARED_SQL = "insert into encounter"
            + " (encounter_id, encounter_type, parent_id, location_id, form_id, " +
            "encounter_datetime, creator, date_created, voided, voided_by, date_voided, " +
            "void_reason, changed_by, date_changed, visit_id, uuid)"
            + " values (:id, :encounterType, :patientId, :encounterId, :locationId, :formId, " +
            ":encounterDatetime, :creator, :dateCreated, :voided, :voidedBy, :dateVoided, " +
            ":voidReason, :changedBy, :dateChanged, :visitId, :uuid)";

    private long encounterType;

    private long patientId;

    private long encounterId;

    private long locationId;

    private long formId;

    private LocalDateTime encounterDatetime;

    private long creator;

    private LocalDateTime dateCreated;

    private long voided;

    private long voidedBy;

    private LocalDateTime dateVoided;

    private String voidReason;

    private long changedBy;

    private LocalDateTime dateChanged;

    private long visitId;
    
    private UUID uuid;

    @Override
    public String getPreparedSql() {
        return PREPARED_SQL;
    }

    @Builder
    public Encounter(long id, long encounterType, long patientId, long encounterId,
                     long locationId, long formId, LocalDateTime encounterDatetime,
                     long creator, LocalDateTime dateCreated, long voided, long voidedBy,
                     LocalDateTime dateVoided, String voidReason, long changedBy,
                     LocalDateTime dateChanged, long visitId, UUID uuid) {
        super(id);
        this.encounterType = encounterType;
        this.patientId = patientId;
        this.encounterId = encounterId;
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

package org.openmrs.isanteplus.performancedata.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Visit extends Entity {

    protected final String TABLE_NAME = "visit";
    protected final String ID_COLUMN = "visit_id";
    protected final String PREPARED_SQL = "insert into " + TABLE_NAME
            + " (visit_id, patient_id, visit_type_id, date_started, date_stopped," +
            " indication_concept_id, location_id, creator, date_created, changed_by," +
            " date_changed, voided, voided_by, date_voided, void_reason, uuid)"
            + " values (:id, :patientId, :visitTypeId, :dateStarted, :dateStopped, " +
            ":indicationConceptId, :locationId, :creator, :dateCreated, :changedBy, " +
            ":dateChanged, :voided, :voidedBy, :dateVoided, :voidReason, :uuid)";

    private long patientId;

    private long visitTypeId;

    private LocalDateTime dateStarted;

    private LocalDateTime dateStopped;

    private long indicationConceptId;

    private long locationId;

    private long creator;

    private LocalDateTime dateCreated;

    private Long changedBy;

    private LocalDateTime dateChanged;

    private Long voided;

    private Long voidedBy;

    private LocalDateTime dateVoided;

    private String voidReason;

    private String uuid;

    @Builder
    public Visit(long id, long patientId, long visitTypeId, LocalDateTime dateStarted,
                 LocalDateTime dateStopped, long indicationConceptId, long locationId,
                 long creator, LocalDateTime dateCreated, long changedBy,
                 LocalDateTime dateChanged, long voided, long voidedBy, LocalDateTime dateVoided,
                 String voidReason, String uuid) {
        super(id);
        this.patientId = patientId;
        this.visitTypeId = visitTypeId;
        this.dateStarted = dateStarted;
        this.dateStopped = dateStopped;
        this.indicationConceptId = indicationConceptId;
        this.locationId = locationId;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.changedBy = changedBy;
        this.dateChanged = dateChanged;
        this.voided = voided;
        this.voidedBy = voidedBy;
        this.dateVoided = dateVoided;
        this.voidReason = voidReason;
        this.uuid = uuid;
    }
}

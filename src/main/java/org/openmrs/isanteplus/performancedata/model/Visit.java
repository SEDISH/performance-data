package org.openmrs.isanteplus.performancedata.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Visit extends AbstractEntity {

    private static final String PREPARED_SQL = "insert into visit"
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

    private long changedBy;

    private LocalDateTime dateChanged;

    private long voided;

    private long voidedBy;

    private LocalDateTime dateVoided;

    private String voidReason;

    private UUID uuid;

    @Override
    public String getPreparedSql() {
        return PREPARED_SQL;
    }

    @Builder
    public Visit(long id, long patientId, long visitTypeId, LocalDateTime dateStarted,
                 LocalDateTime dateStopped, long indicationConceptId, long locationId,
                 long creator, LocalDateTime dateCreated, long changedBy,
                 LocalDateTime dateChanged, long voided, long voidedBy, LocalDateTime dateVoided,
                 String voidReason, UUID uuid) {
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

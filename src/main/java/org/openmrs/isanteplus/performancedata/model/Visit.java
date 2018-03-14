package org.openmrs.isanteplus.performancedata.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Visit extends AbstractEntity {

    private static final String PREPARED_SQL = "insert into visit"
            + " (visit_id, patient_id, visit_type_id, date_started, date_stopped," +
            " indication_concept_id, location_id, creator, date_created, changed_by," +
            " date_changed, voided, voided_by, date_voided, void_reason, uuid)"
            + " values (:visitId, :patientId, :visitTypeId, :dateStarted, :dateStopped, " +
            ":indicationConceptId, :locationId, :creator, :dateCreated, :changedBy, " +
            ":dateChanged, :voided, :voidedBy, :dateVoided, :voidReason, :uuid)";

    private long visitId;

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
}

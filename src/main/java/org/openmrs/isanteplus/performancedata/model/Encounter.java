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
public class Encounter extends AbstractEntity {

    private static final String PREPARED_SQL = "insert into encounter"
            + " (encounter_id, encounter_type, parent_id, location_id, form_id, " +
            "encounter_datetime, creator, date_created, voided, voided_by, date_voided, " +
            "void_reason, changed_by, date_changed, visit_id, uuid)"
            + " values (:encounterType, :patientId, :encounterId, :locationId, :formId, " +
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
}


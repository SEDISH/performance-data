package org.openmrs.isanteplus.performancedata.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Patient extends AbstractEntity {

    private static final String PREPARED_SQL = "insert into patient"
            + " (patient_id, creator, date_created, changed_by, date_changed, voided," +
            " voided_by, date_voided, void_reason, allergy_status)"
            + " values (:patientId, :creator, :dateCreated, :changedBy, :dateChanged, " +
            ":voided, :voidedBy, :dateVoided, :voidReason, :allergyStatus)";

    private long patientId;

    private long creator;

    private LocalDateTime dateCreated;

    private long changedBy;

    private LocalDateTime dateChanged;

    private long voided;

    private long voidedBy;

    private LocalDateTime dateVoided;

    private String voidReason;

    private String allergyStatus;

    @Override
    public String getPreparedSql() {
        return PREPARED_SQL;
    }
}

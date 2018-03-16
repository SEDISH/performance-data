package org.openmrs.isanteplus.performancedata.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
public class Patient extends AbstractEntity {

    private static final String PREPARED_SQL = "insert into patient"
            + " (patient_id, creator, date_created, changed_by, date_changed, voided," +
            " voided_by, date_voided, void_reason, allergy_status)"
            + " values (:id, :creator, :dateCreated, :changedBy, :dateChanged, " +
            ":voided, :voidedBy, :dateVoided, :voidReason, :allergyStatus)";

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

    @Builder
    public Patient(long id, long creator, LocalDateTime dateCreated, long changedBy,
                   LocalDateTime dateChanged, long voided, long voidedBy,
                   LocalDateTime dateVoided, String voidReason, String allergyStatus) {
        super(id);
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.changedBy = changedBy;
        this.dateChanged = dateChanged;
        this.voided = voided;
        this.voidedBy = voidedBy;
        this.dateVoided = dateVoided;
        this.voidReason = voidReason;
        this.allergyStatus = allergyStatus;
    }
}

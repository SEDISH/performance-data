package org.openmrs.isanteplus.performancedata.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Patient extends Entity {

    protected final String ID_COLUMN = "patient_id";
    protected final String TABLE_NAME = "patient";
    protected final String PREPARED_SQL = "insert into " + TABLE_NAME
            + " (patient_id, creator, date_created, changed_by, date_changed, voided," +
            " voided_by, date_voided, void_reason)"
            + " values (:id, :creator, :dateCreated, :changedBy, :dateChanged, " +
            ":voided, :voidedBy, :dateVoided, :voidReason)";

    private Long changedBy;
    private LocalDateTime dateChanged;

    @Builder
    public Patient(long id, long creator, LocalDateTime dateCreated, Long changedBy,
                   LocalDateTime dateChanged, long voided, Long voidedBy,
                   LocalDateTime dateVoided, String voidReason) {
        super(id, creator, dateCreated, voided, voidedBy, dateVoided, voidReason);
        this.changedBy = changedBy;
        this.dateChanged = dateChanged;
    }

    @Override
    public String getSelect(long limit, long offset) {
        return getSelect(ID_COLUMN, TABLE_NAME, limit, offset);
    }

    @Override
    public String getCount() {
        return getCount(TABLE_NAME);
    }
}

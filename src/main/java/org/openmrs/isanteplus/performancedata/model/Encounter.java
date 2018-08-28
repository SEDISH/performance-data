package org.openmrs.isanteplus.performancedata.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Encounter extends Entity {

    protected final String TABLE_NAME = "encounter";
    protected final String ID_COLUMN = "encounter_id";
    protected final String TYPE_COLUMN = "encounter_type";
    protected final String PATIENT_ID_COLUMN = "patient_id";
    protected final String LOCATION_COLUMN = "location_id";
    protected final String FORM_COLUMN = "form_id";
    protected final String DATE_TIME_COLUMN = "encounter_datetime";
    protected final String CHANGED_BY_COLUMN = "changed_by";
    protected final String DATE_CHANGED_COLUMN = "date_changed";
    protected final String VISIT_COLUMN = "visit_id";
    protected final String UUID_COLUMN = "uuid";
    protected final String PREPARED_SQL = "insert into " + TABLE_NAME
            + " (" + ID_COLUMN + ", " + TYPE_COLUMN + ", " + PATIENT_ID_COLUMN + ", " + LOCATION_COLUMN + ", " + FORM_COLUMN
            + ", " + DATE_TIME_COLUMN + ", " + CREATOR_COLUMN + ", " + DATE_CREATED_COLUMN + ", " + VOIDED_COLUMN + ", "
            + VOIDED_BY_COLUMN + ", " + DATE_VOIDED_COLUMN + ", " + VOID_REASON_COLUMN + ", " + CHANGED_BY_COLUMN + ", "
            + DATE_CHANGED_COLUMN + ", " + VISIT_COLUMN + ", " + UUID_COLUMN + ")"
            + " values (:id, :encounterType, :patientId, :locationId, :formId, " +
            ":encounterDatetime, :creator, :dateCreated, :voided, :voidedBy, :dateVoided, " +
            ":voidReason, :changedBy, :dateChanged, :visitId, :uuid)";

    private long encounterType;
    private long patientId;
    private long locationId;
    private long formId;
    private LocalDateTime encounterDatetime;
    private Long changedBy;
    private LocalDateTime dateChanged;
    private long visitId;
    private String uuid;

    @Builder
    public Encounter(long id, long creator, LocalDateTime dateCreated, long voided, Long voidedBy, LocalDateTime dateVoided,
                     String voidReason, Long changedBy, LocalDateTime dateChanged, String uuid, long encounterType,
                     long patientId, long locationId, long formId, LocalDateTime encounterDatetime, long visitId) {
        super(id, creator, dateCreated, voided, voidedBy, dateVoided, voidReason);
        this.encounterType = encounterType;
        this.patientId = patientId;
        this.locationId = locationId;
        this.formId = formId;
        this.encounterDatetime = encounterDatetime;
        this.changedBy = changedBy;
        this.dateChanged = dateChanged;
        this.visitId = visitId;
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}

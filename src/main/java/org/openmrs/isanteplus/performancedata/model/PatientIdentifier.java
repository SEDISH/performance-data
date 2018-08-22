package org.openmrs.isanteplus.performancedata.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PatientIdentifier extends Entity {

    protected final String TABLE_NAME = "patient_identifier";
    protected final String ID_COLUMN = "patient_identifier_id";
    protected final String PREPARED_SQL = "insert into " + TABLE_NAME
            + " (patient_identifier_id, patient_id, identifier, identifier_type, preferred, location_id, " +
            "creator, date_created, date_changed, changed_by, voided, voided_by, date_voided, void_reason, uuid)"
            + " values (:id, :patientId, :identifier, :identifierType, :preferred, :locationId, " +
            ":creator, :dateCreated, :dateChanged, :changedBy, :voided, :voidedBy, :dateVoided, :voidReason, :uuid)";

    private long patientId;
    private String identifier;
    private long identifierType;
    private long preferred;
    private Long locationId;
    private long creator;
    private LocalDateTime dateCreated;
    private LocalDateTime dateChanged;
    private Long changedBy;
    private long voided;
    private long voidedBy;
    private LocalDateTime dateVoided;
    private String voidReason;
    private String uuid;

    @Builder
    public PatientIdentifier(long id, long patientId, String identifier, long identifierType, long preferred,
                             Long locationId, long creator, LocalDateTime dateCreated, LocalDateTime dateChanged,
                             Long changedBy, long voided, long voidedBy, LocalDateTime dateVoided, String voidReason,
                             String uuid) {
        super(id);
        this.patientId = patientId;
        this.identifier = identifier;
        this.identifierType = identifierType;
        this.preferred = preferred;
        this.locationId = locationId;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.dateChanged = dateChanged;
        this.changedBy = changedBy;
        this.voided = voided;
        this.voidedBy = voidedBy;
        this.dateVoided = dateVoided;
        this.voidReason = voidReason;
        this.uuid = uuid;
    }
}
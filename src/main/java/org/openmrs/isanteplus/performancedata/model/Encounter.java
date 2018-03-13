package org.openmrs.isanteplus.performancedata.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Encounter extends AbstractEntity {

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

    public Encounter() {
        preparedSql = "insert into encounter"
                + " (encounter_id, encounter_type, parent_id, location_id, form_id, " +
                "encounter_datetime, creator, date_created, voided, voided_by, date_voided, " +
                "void_reason, changed_by, date_changed, visit_id, uuid)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public PreparedStatement fillPreparedStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, Long.toString(encounterType));
        ps.setString(2, Long.toString(patientId));
        ps.setString(3, Long.toString(encounterId));
        ps.setString(4, Long.toString(locationId));
        ps.setString(5, Long.toString(formId));
        ps.setString(6, encounterDatetime.toString());
        ps.setString(7, Long.toString(creator));
        ps.setString(8, dateCreated.toString());
        ps.setString(9, Long.toString(voided));
        ps.setString(10, Long.toString(voidedBy));
        ps.setString(11, dateVoided.toString());
        ps.setString(12, voidReason);
        ps.setString(13, Long.toString(changedBy));
        ps.setString(14, dateChanged.toString());
        ps.setString(15, Long.toString(visitId));
        ps.setString(16, uuid.toString());

        ps.addBatch();

        return ps;
    }
}


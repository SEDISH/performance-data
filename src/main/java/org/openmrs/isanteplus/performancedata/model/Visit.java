package org.openmrs.isanteplus.performancedata.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Visit extends AbstractEntity {

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

    public Visit() {
        preparedSql = "insert into visit"
                + " (visit_id, patient_id, visit_type_id, date_started, date_stopped," +
                " indication_concept_id, location_id, creator, date_created, changed_by," +
                " date_changed, voided, voided_by, date_voided, void_reason, uuid)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public PreparedStatement fillPreparedStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, Long.toString(visitId));
        ps.setString(2, Long.toString(patientId));
        ps.setString(3, Long.toString(visitTypeId));
        ps.setString(4, dateStarted.toString());
        ps.setString(5, dateStopped.toString());
        ps.setString(6, Long.toString(indicationConceptId));
        ps.setString(7, Long.toString(locationId));
        ps.setString(8, Long.toString(creator));
        ps.setString(9, dateCreated.toString());
        ps.setString(10, Long.toString(changedBy));
        ps.setString(11, dateChanged.toString());
        ps.setString(12, Long.toString(voided));
        ps.setString(13, Long.toString(voidedBy));
        ps.setString(14, dateVoided.toString());
        ps.setString(15, voidReason);
        ps.setString(16, uuid.toString());

        ps.addBatch();

        return ps;
    }
}

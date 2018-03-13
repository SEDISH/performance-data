package org.openmrs.isanteplus.performancedata.model;

import lombok.Getter;
import lombok.Setter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Getter
@Setter
public class Patient extends AbstractEntity {

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

    public Patient() {
        preparedSql = "insert into patient"
                + " (patient_id, creator, date_created, changed_by, date_changed, voided, voided_by" +
                ", date_voided, void_reason, allergy_status)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public PreparedStatement fillPreparedStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, Long.toString(patientId));
        ps.setString(2, Long.toString(creator));
        ps.setString(3, dateCreated.toString());
        ps.setString(4, Long.toString(changedBy));
        ps.setString(5, dateChanged.toString());
        ps.setString(6, Long.toString(voided));
        ps.setString(7, Long.toString(voidedBy));
        ps.setString(8, dateVoided.toString());
        ps.setString(9, voidReason);
        ps.setString(10, allergyStatus);

        ps.addBatch();

        return ps;
    }
}

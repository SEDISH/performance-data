package org.openmrs.isanteplus.performancedata.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Obs extends AbstractEntity {

    private long obsId;

    private long personId;

    private long conceptId;

    private long encounterId;

    private long orderId;

    private LocalDateTime obsDatetime;

    private long locationId;

    private long obsGroupId;

    private String accessionNumber;

    private long valueGroupId;

    private long valueCoded;

    private long valueCodedNameId;

    private long valueDrug;

    private LocalDateTime valueDatetime;

    private double valueNumeric;

    private String valueModifier;

    private String valueText;

    private String valueComplex;

    private String comments;

    private long creator;

    private LocalDateTime dateCreated;

    private long voided;

    private long voidedBy;

    private LocalDateTime dateVoided;

    private String voidReason;

    private UUID uuid;

    private long previousVersion;

    private String formNamespaceAndPath;

    public Obs() {
        preparedSql = "insert into patient" +
                " (obs_id, person_id, concept_id, encounter_id, order_id, obs_datetime, " +
                "location_id, obs_group_id, accession_number, value_group_id, value_coded," +
                "value_coded_name_id, value_drug, value_datetime, value_numeric, value_numeric," +
                "value_modifier, value_text, value_complex, comments, creator, date_created, " +
                "voided, voided_by, date_voided, void_reason, uuid, previous_version, " +
                "form_namespace_and_path)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                " ?, ?, ?, ?, ?)";
    }

    @Override
    public PreparedStatement fillPreparedStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, Long.toString(obsId));
        ps.setString(2, Long.toString(personId));
        ps.setString(3, Long.toString(conceptId));
        ps.setString(4, Long.toString(encounterId));
        ps.setString(5, Long.toString(orderId));
        ps.setString(6, obsDatetime.toString());
        ps.setString(7, Long.toString(locationId));
        ps.setString(8, Long.toString(obsGroupId));
        ps.setString(9, accessionNumber);
        ps.setString(10, Long.toString(valueGroupId));
        ps.setString(11, Long.toString(valueCoded));
        ps.setString(12, Long.toString(valueCodedNameId));
        ps.setString(13, Long.toString(valueDrug));
        ps.setString(14, valueDatetime.toString());
        ps.setString(15, Double.toString(valueNumeric));
        ps.setString(16, valueModifier);
        ps.setString(17, valueText);
        ps.setString(18, valueComplex);
        ps.setString(19, comments);
        ps.setString(20, Long.toString(creator));
        ps.setString(21, dateCreated.toString());
        ps.setString(22, Long.toString(voided));
        ps.setString(23, Long.toString(voidedBy));
        ps.setString(24, dateVoided.toString());
        ps.setString(25, voidReason);
        ps.setString(26, uuid.toString());
        ps.setString(27, Long.toString(previousVersion));
        ps.setString(28, formNamespaceAndPath);

        ps.addBatch();

        return ps;
    }
}

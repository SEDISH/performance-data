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
public class Obs extends AbstractEntity {

    private static final String PREPARED_SQL = "insert into patient" +
            " (obs_id, person_id, concept_id, encounter_id, order_id, obs_datetime, " +
            "location_id, obs_group_id, accession_number, value_group_id, value_coded," +
            "value_coded_name_id, value_drug, value_datetime, value_numeric, value_numeric," +
            "value_modifier, value_text, value_complex, comments, creator, date_created, " +
            "voided, voided_by, date_voided, void_reason, uuid, previous_version, " +
            "form_namespace_and_path)" +
            " values (:obsId, :personId, :conceptId, :encounterId, :orderId, :obsDatetime," +
            " :locationId, :obsGroupId, :accessionNumber, :valueGroupId, :valueCoded," +
            " :valueCodedNameId, :valueDrug, :valueDatetime, :valueNumeric, :valueModifier," +
            " :valueText, :valueComplex, :comments, :creator, :dateCreated, :voided," +
            " :voidedBy, :dateVoided, :voidReason, :uuid, :previousVersion," +
            " :formNamespaceAndPath)";

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

    @Override
    public String getPreparedSql() {
        return PREPARED_SQL;
    }
}

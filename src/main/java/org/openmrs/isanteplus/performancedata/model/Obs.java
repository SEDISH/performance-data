package org.openmrs.isanteplus.performancedata.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Obs extends AbstractEntity {

    private static final String PREPARED_SQL = "insert into patient" +
            " (obs_id, person_id, concept_id, encounter_id, order_id, obs_datetime, " +
            "location_id, obs_group_id, accession_number, value_group_id, value_coded," +
            "value_coded_name_id, value_drug, value_datetime, value_numeric, value_numeric," +
            "value_modifier, value_text, value_complex, comments, creator, date_created, " +
            "voided, voided_by, date_voided, void_reason, uuid, previous_version, " +
            "form_namespace_and_path)" +
            " values (:id, :personId, :conceptId, :encounterId, :orderId, :obsDatetime," +
            " :locationId, :obsGroupId, :accessionNumber, :valueGroupId, :valueCoded," +
            " :valueCodedNameId, :valueDrug, :valueDatetime, :valueNumeric, :valueModifier," +
            " :valueText, :valueComplex, :comments, :creator, :dateCreated, :voided," +
            " :voidedBy, :dateVoided, :voidReason, :uuid, :previousVersion," +
            " :formNamespaceAndPath)";

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

    @Builder
    public Obs(long id, long personId, long conceptId, long encounterId, long orderId,
               LocalDateTime obsDatetime, long locationId, long obsGroupId,
               String accessionNumber, long valueGroupId, long valueCoded,
               long valueCodedNameId, long valueDrug, LocalDateTime valueDatetime,
               double valueNumeric, String valueModifier, String valueText, String valueComplex,
               String comments, long creator, LocalDateTime dateCreated, long voided,
               long voidedBy, LocalDateTime dateVoided, String voidReason, UUID uuid,
               long previousVersion, String formNamespaceAndPath) {
        super(id);
        this.personId = personId;
        this.conceptId = conceptId;
        this.encounterId = encounterId;
        this.orderId = orderId;
        this.obsDatetime = obsDatetime;
        this.locationId = locationId;
        this.obsGroupId = obsGroupId;
        this.accessionNumber = accessionNumber;
        this.valueGroupId = valueGroupId;
        this.valueCoded = valueCoded;
        this.valueCodedNameId = valueCodedNameId;
        this.valueDrug = valueDrug;
        this.valueDatetime = valueDatetime;
        this.valueNumeric = valueNumeric;
        this.valueModifier = valueModifier;
        this.valueText = valueText;
        this.valueComplex = valueComplex;
        this.comments = comments;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.voided = voided;
        this.voidedBy = voidedBy;
        this.dateVoided = dateVoided;
        this.voidReason = voidReason;
        this.uuid = uuid;
        this.previousVersion = previousVersion;
        this.formNamespaceAndPath = formNamespaceAndPath;
    }
}

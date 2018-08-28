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
public class Obs extends Entity {

    private final String TABLE_NAME = "obs";
    private final String ID_COLUMN = "obs_id";
    private final String PREPARED_SQL = "insert into " + TABLE_NAME +
            " (obs_id, person_id, concept_id, encounter_id, order_id, obs_datetime, " +
            "location_id, obs_group_id, accession_number, value_group_id, value_boolean, value_coded," +
            "value_coded_name_id, value_drug, value_datetime, value_numeric," +
            "value_modifier, value_text, value_complex, comments, creator, date_created, " +
            "voided, voided_by, date_voided, void_reason, uuid, previous_version, " +
            "form_namespace_and_path)" +
            " values (:id, :personId, :conceptId, :encounterId, :orderId, :obsDatetime," +
            " :locationId, :obsGroupId, :accessionNumber, :valueGroupId, :valueBoolean, :valueCoded," +
            " :valueCodedNameId, :valueDrug, :valueDatetime, :valueNumeric, :valueModifier," +
            " :valueText, :valueComplex, :comments, :creator, :dateCreated, :voided," +
            " :voidedBy, :dateVoided, :voidReason, :uuid, :previousVersion," +
            " :formNamespaceAndPath)";

    private long personId;

    private long conceptId;

    private long encounterId;

    private Long orderId;

    private LocalDateTime obsDatetime;

    private long locationId;

    private Long obsGroupId;

    private String accessionNumber;

    private Long valueGroupId;

    private Long valueBoolean;

    private Long valueCoded;

    private Long valueCodedNameId;

    private Long valueDrug;

    private LocalDateTime valueDatetime;

    private Double valueNumeric;

    private String valueModifier;

    private String valueText;

    private String valueComplex;

    private String comments;

    private String uuid;

    private Long previousVersion;

    private String formNamespaceAndPath;

    @Builder
    public Obs(long id, long personId, long conceptId, long encounterId, Long orderId, LocalDateTime obsDatetime,
               long locationId, Long obsGroupId, String accessionNumber, Long valueGroupId, Long valueBoolean,
               Long valueCoded, Long valueCodedNameId, Long valueDrug, LocalDateTime valueDatetime, Double valueNumeric,
               String valueModifier, String valueText, String valueComplex, String comments, long creator,
               LocalDateTime dateCreated, long voided, Long voidedBy, LocalDateTime dateVoided, String voidReason,
               String uuid, Long previousVersion, String formNamespaceAndPath) {
        super(id, creator, dateCreated, voided, voidedBy, dateVoided, voidReason);
        this.personId = personId;
        this.conceptId = conceptId;
        this.encounterId = encounterId;
        this.orderId = orderId;
        this.obsDatetime = obsDatetime;
        this.locationId = locationId;
        this.obsGroupId = obsGroupId;
        this.accessionNumber = accessionNumber;
        this.valueGroupId = valueGroupId;
        this.valueBoolean = valueBoolean;
        this.valueCoded = valueCoded;
        this.valueCodedNameId = valueCodedNameId;
        this.valueDrug = valueDrug;
        this.valueDatetime = valueDatetime;
        this.valueNumeric = valueNumeric;
        this.valueModifier = valueModifier;
        this.valueText = valueText;
        this.valueComplex = valueComplex;
        this.comments = comments;
        this.uuid = uuid;
        this.previousVersion = previousVersion;
        this.formNamespaceAndPath = formNamespaceAndPath;
    }

    @Override
    public String getCount() {
        return getCount(TABLE_NAME);
    }

    @Override
    public String getLastID() {
        return "SELECT MAX(" + ID_COLUMN + ") as " + COUNT_ALIAS + " FROM " + TABLE_NAME;
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

package org.openmrs.isanteplus.performancedata.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PersonName extends Entity {

    protected final String TABLE_NAME = "person_name";
    protected final String ID_COLUMN = "person_name_id";
    protected final String PREPARED_SQL = "insert into " + TABLE_NAME
            + " (person_name_id, preferred, person_id, prefix, given_name, middle_name, " +
            "family_name_prefix, family_name, family_name2, family_name_suffix, degree, creator, date_created, " +
            "voided, voided_by, date_voided, void_reason, changed_by, date_changed, uuid)"
            + " values (:id, :preferred, :personId, :prefix, :givenName, :middleName, :familyNamePrefix, :familyName," +
            " :familyName2, :familyNameSuffix, :degree, :creator, :dateCreated, :voided, :voidedBy," +
            " :dateVoided, :voidReason, :changedBy, :dateChanged, :uuid)";

    private long preferred;
    private long personId;
    private String prefix;
    private String givenName;
    private String middleName;
    private String familyNamePrefix;
    private String familyName;
    private String familyName2;
    private String familyNameSuffix;
    private String degree;
    private long creator;
    private LocalDateTime dateCreated;
    private Long voided;
    private Long voidedBy;
    private LocalDateTime dateVoided;
    private String voidReason;
    private Long changedBy;
    private LocalDateTime dateChanged;
    private String uuid;

    @Builder
    public PersonName(long id, long preferred, long personId, String prefix, String givenName, String middleName,
                      String familyNamePrefix, String familyName, String familyName2, String familyNameSuffix,
                      String degree, long creator, LocalDateTime dateCreated, long voided, long voidedBy,
                      LocalDateTime dateVoided, String voidReason, Long changedBy, LocalDateTime dateChanged, String uuid) {
        super(id);
        this.preferred = preferred;
        this.personId = personId;
        this.prefix = prefix;
        this.givenName = givenName;
        this.middleName = middleName;
        this.familyNamePrefix = familyNamePrefix;
        this.familyName = familyName;
        this.familyName2 = familyName2;
        this.familyNameSuffix = familyNameSuffix;
        this.degree = degree;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.voided = voided;
        this.voidedBy = voidedBy;
        this.dateVoided = dateVoided;
        this.voidReason = voidReason;
        this.changedBy = changedBy;
        this.dateChanged = dateChanged;
        this.uuid = uuid;
    }
}

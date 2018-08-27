package org.openmrs.isanteplus.performancedata.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PersonAddress extends Entity {

    protected final String TABLE_NAME = "person_address";
    protected final String ID_COLUMN = "person_address_id";
    protected final String PREPARED_SQL = "insert into " + TABLE_NAME
            + " (person_address_id, person_id, preferred, address1, address2, city_village, " +
            "state_province, postal_code, country, latitude, longitude, start_date, end_date, creator, date_created, " +
            "voided, voided_by, date_voided, void_reason, county_district, address3, address4, address5, address6," +
            " date_changed, changed_by, uuid)"
            + " values (:id, :personId, :preferred, :address1, :address2, :cityVillage, :stateProvince, :postalCode," +
            " :country, :latitude, :longitude, :startDate, :endDate, :creator, :dateCreated, :voided, :voidedBy," +
            " :dateVoided, :voidReason, :countyDistrict, :address3, :address4, :address5, :address6," +
            " :dateChanged, :changedBy, :uuid)";

    private long personId;

    private long preferred;

    private String address1;

    private String address2;

    private String cityVillage;

    private String stateProvince;

    private String postalCode;

    private String country;

    private String latitude;

    private String longitude;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private long creator;

    private LocalDateTime dateCreated;

    private Long voided;

    private Long voidedBy;

    private LocalDateTime dateVoided;

    private String voidReason;

    private String countyDistrict;

    private String address3;

    private String address4;

    private String address5;

    private String address6;

    private LocalDateTime dateChanged;

    private Long changedBy;

    private String uuid;

    @Builder
    public PersonAddress(long id, long personId, long preferred, String address1, String address2, String cityVillage,
                         String stateProvince, String postalCode, String country, String latitude, String longitude,
                         LocalDateTime startDate, LocalDateTime endDate, long creator, LocalDateTime dateCreated,
                         long voided, long voidedBy, LocalDateTime dateVoided, String voidReason, String countyDistrict,
                         String address3, String address4, String address5, String address6, LocalDateTime dateChanged,
                         Long changedBy, String uuid) {
        super(id);
        this.personId = personId;
        this.preferred = preferred;
        this.address1 = address1;
        this.address2 = address2;
        this.cityVillage = cityVillage;
        this.stateProvince = stateProvince;
        this.postalCode = postalCode;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startDate = startDate;
        this.endDate = endDate;
        this.creator = creator;
        this.dateCreated = dateCreated;
        this.voided = voided;
        this.voidedBy = voidedBy;
        this.dateVoided = dateVoided;
        this.voidReason = voidReason;
        this.countyDistrict = countyDistrict;
        this.address3 = address3;
        this.address4 = address4;
        this.address5 = address5;
        this.address6 = address6;
        this.dateChanged = dateChanged;
        this.changedBy = changedBy;
        this.uuid = uuid;
    }
}

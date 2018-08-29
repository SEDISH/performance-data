package org.openmrs.isanteplus.performancedata.model.mapper;

import org.openmrs.isanteplus.performancedata.model.Encounter;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EncounterMapper implements RowMapper<Encounter> {
    public Encounter mapRow(ResultSet rs, int rowNum) throws SQLException {
        Encounter encounter = new Encounter();
        encounter.setId(rs.getLong(encounter.getID_COLUMN()));
        encounter.setCreator(rs.getLong(encounter.getCREATOR_COLUMN()));
        encounter.setDateCreated(encounter.parseDateTimeFromDatabase(rs.getString(encounter.getDATE_CREATED_COLUMN())));
        encounter.setVoided(rs.getLong(encounter.getVOIDED_COLUMN()));
        encounter.setVoidedBy(rs.getLong(encounter.getVOIDED_BY_COLUMN()));
        encounter.setDateVoided(encounter.parseDateTimeFromDatabase(rs.getString(encounter.getDATE_VOIDED_COLUMN())));
        encounter.setVoidReason(rs.getString(encounter.getVOID_REASON_COLUMN()));
        encounter.setChangedBy(rs.getLong(encounter.getCHANGED_BY_COLUMN()));
        encounter.setDateChanged(encounter.parseDateTimeFromDatabase(rs.getString(encounter.getDATE_CHANGED_COLUMN())));
        encounter.setUuid(rs.getString(encounter.getUUID_COLUMN()));
        encounter.setEncounterType(rs.getInt(encounter.getTYPE_COLUMN()));
        encounter.setPatientId(rs.getLong(encounter.getPATIENT_ID_COLUMN()));
        encounter.setLocationId(rs.getLong(encounter.getLOCATION_COLUMN()));
        encounter.setFormId(rs.getLong(encounter.getFORM_COLUMN()));
        encounter.setEncounterDatetime(encounter.parseDateTimeFromDatabase(rs.getString(encounter.getDATE_TIME_COLUMN())));
        encounter.setVisitId(rs.getLong(encounter.getVISIT_COLUMN()));
        return encounter;
    }
}

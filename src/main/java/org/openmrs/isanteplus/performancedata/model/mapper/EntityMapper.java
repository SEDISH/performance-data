package org.openmrs.isanteplus.performancedata.model.mapper;

import org.openmrs.isanteplus.performancedata.model.Entity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;



public class EntityMapper implements RowMapper<Entity> {
    public Entity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Entity entity = new Entity();
        entity.setId(rs.getLong(entity.getID_COLUMN()));
        entity.setCreator(rs.getLong(entity.getCREATOR_COLUMN()));
        entity.setDateCreated(entity.parseDateTimeFromDatabase(rs.getString(entity.getDATE_CREATED_COLUMN())));
        entity.setVoided(rs.getLong(entity.getVOIDED_COLUMN()));
        entity.setVoidedBy(rs.getLong(entity.getVOIDED_BY_COLUMN()));
        entity.setDateVoided(entity.parseDateTimeFromDatabase(rs.getString(entity.getDATE_VOIDED_COLUMN())));
        entity.setVoidReason(rs.getString(entity.getVOID_REASON_COLUMN()));
        return entity;
    }

}

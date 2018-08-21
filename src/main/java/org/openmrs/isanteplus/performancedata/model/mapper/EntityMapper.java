package org.openmrs.isanteplus.performancedata.model.mapper;

import org.openmrs.isanteplus.performancedata.model.Entity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityMapper implements RowMapper<Entity> {
    public Entity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Entity entity = new Entity();
        entity.setId(rs.getInt(entity.getID_COLUMN()));
        return entity;
    }
}

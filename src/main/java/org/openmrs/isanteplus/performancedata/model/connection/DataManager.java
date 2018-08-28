package org.openmrs.isanteplus.performancedata.model.connection;

import org.openmrs.isanteplus.performancedata.generator.util.ChunkKeeper;
import org.openmrs.isanteplus.performancedata.model.Entity;
import org.openmrs.isanteplus.performancedata.model.mapper.EntityMapper;
import org.openmrs.isanteplus.performancedata.options.GeneratorOptions;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DataManager {

    private final MySqlConnector connector;

    private final long batchNumber;

    public DataManager(GeneratorOptions options, long batchNumber, long packetSize)
            throws PropertyVetoException {
        this.connector = new MySqlConnector(options.getDbLogin(), options.getDbPassword(),
                options.getDbServer(), options.getDbPort(), options.getDbName(), packetSize);
        this.batchNumber = batchNumber;
    }

    public void insertEntities(List<Entity> entities) {
        if (!CollectionUtils.isEmpty(entities)) {
            ChunkKeeper chunkKeeper = new ChunkKeeper(entities.size(), batchNumber);
            String sql = entities.iterator().next().getPREPARED_SQL();
            NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
                    connector.getCpds());

            while (chunkKeeper.hasNext()) {
                List chunk = chunkKeeper.getChunkFromList(entities);
                SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(chunk);

                template.batchUpdate(sql, batch);
            }
        }
    }

    public  List<Entity> fetchEntities(String query) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
                connector.getCpds());
        return template.query(query, new EntityMapper());
    }

    public void closePool() {
        connector.closePool();
    }

    public long getCount(Entity entity) throws SQLException {
        Statement st = connector.createStatement();
        String query = entity.getCount();
        ResultSet rs = st.executeQuery(query);
        if (rs.next()) {
            return rs.getLong(entity.COUNT_ALIAS);
        } else {
            throw new SQLException(query+ "; couldn't get any result");
        }
    }
}

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

    private final String COUNT_COLUMN = "size";
    private final String SELECT_COUNT = "SELECT COUNT(*) as " + COUNT_COLUMN + " FROM ";
    private final String ID_COLUMN = "id";

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

    public  List<Entity> fetchEntities(String tableName, String entityIdName, long limit, long offset) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
                connector.getCpds());
        return template.query("SELECT "+ entityIdName + " as " + ID_COLUMN + " FROM " + tableName + " LIMIT "
                + limit + " OFFSET " + offset,
                new EntityMapper());
    }

    public void closePool() {
        connector.closePool();
    }

    public long getCount(String tableName) throws SQLException {
        Statement st = connector.createStatement();
        ResultSet rs = st.executeQuery(SELECT_COUNT + tableName);
        if (rs.next()) {
            return rs.getLong(COUNT_COLUMN);
        } else {
            throw new SQLException(SELECT_COUNT + tableName + "; couldn't get any result");
        }
    }
}

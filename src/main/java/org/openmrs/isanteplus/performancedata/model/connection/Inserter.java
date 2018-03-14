package org.openmrs.isanteplus.performancedata.model.connection;

import org.openmrs.isanteplus.performancedata.model.AbstractEntity;
import org.openmrs.isanteplus.performancedata.options.GeneratorOptions;

import java.sql.SQLException;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class Inserter {

    private final MySqlConnector connector;

    public Inserter(GeneratorOptions options) throws SQLException {
        this.connector = new MySqlConnector(options.getDbLogin(), options.getDbPassword(),
                options.getDbServer(), options.getDbPort(), options.getDbName());
    }

    public void connect() throws SQLException {
        connector.connect();
    }

    public void disconnect() throws SQLException {
        connector.disconnect();
    }

    public void insertEntities(List<AbstractEntity> entities) {

        if (!CollectionUtils.isEmpty(entities)) {
            String sql = entities.get(0).getPreparedSql();

            SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(entities.toArray());
            NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
                    connector.getDataSource());

            template.batchUpdate(sql, batch);
        }
    }
}

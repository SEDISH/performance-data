package org.openmrs.isanteplus.performancedata.model.connection;

import org.openmrs.isanteplus.performancedata.model.AbstractEntity;
import org.openmrs.isanteplus.performancedata.options.GeneratorOptions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.util.CollectionUtils;
import java.util.List;

public class Inserter {

    private final MySqlConnector connector;

    public Inserter(GeneratorOptions options) {
        this.connector = new MySqlConnector(options.getDbLogin(), options.getDbPassword(),
                options.getDbServer(), options.getDbPort());
    }

    public void insertEntities(List<AbstractEntity> entities) throws SQLException {
            Connection connection = connector.getConnection();

            if (CollectionUtils.isEmpty(entities)) {
                PreparedStatement ps = connection.prepareStatement(
                        entities.get(0).getPreparedSql());

                for (AbstractEntity entity : entities) {
                    ps = entity.fillPreparedStatement(ps);
                }

                ps.executeBatch();
                ps.close();
            }

            connection.close();
    }
}

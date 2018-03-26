package org.openmrs.isanteplus.performancedata.model.connection;

import org.openmrs.isanteplus.performancedata.generator.util.ChunkKeeper;
import org.openmrs.isanteplus.performancedata.model.AbstractEntity;
import org.openmrs.isanteplus.performancedata.options.GeneratorOptions;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class Inserter {

    private final MySqlConnector connector;

    private final long insertNumber;

    public Inserter(GeneratorOptions options, long insertNumber, long packetSize) {
        this.connector = new MySqlConnector(options.getDbLogin(), options.getDbPassword(),
                options.getDbServer(), options.getDbPort(), options.getDbName(), packetSize);
        this.insertNumber = insertNumber;
    }

    public void insertEntities(List<AbstractEntity> entities) {
        if (!CollectionUtils.isEmpty(entities)) {
            ChunkKeeper chunkKeeper = new ChunkKeeper(entities.size(), insertNumber);
            String sql = entities.iterator().next().getPreparedSql();

            while (chunkKeeper.hasNext()) {
                List chunk = chunkKeeper.getChunkFromList(entities);
                SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(chunk);
                NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
                        connector.getDataSource());

                template.batchUpdate(sql, batch);
            }
        }
    }
}

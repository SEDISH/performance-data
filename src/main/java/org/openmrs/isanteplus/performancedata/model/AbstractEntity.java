package org.openmrs.isanteplus.performancedata.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractEntity {

    protected String preparedSql;

    public abstract PreparedStatement fillPreparedStatement(PreparedStatement ps) throws
            SQLException;

    public String getPreparedSql() {
        return preparedSql;
    }
}

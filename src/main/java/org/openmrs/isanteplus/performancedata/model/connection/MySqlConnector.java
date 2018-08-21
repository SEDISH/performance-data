package org.openmrs.isanteplus.performancedata.model.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.Getter;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnector {

    private String login;

    private String password;

    private String server;

    private String port;

    private String dbName;

    private long packetSize;

    @Getter
    private ComboPooledDataSource cpds;

    public MySqlConnector(String login, String password, String server, String port,
                          String dbName, long packetSize) throws PropertyVetoException {
        this.login = login;
        this.password = password;
        this.server = server;
        this.port = port;
        this.dbName = dbName;
        this.packetSize = packetSize;
        this.cpds = setComboPooledDataSource();
    }

    public void closePool() {
        cpds.close();
    }

    public Statement createStatement() throws SQLException {
        return cpds.getConnection().createStatement();
    }

    private ComboPooledDataSource setComboPooledDataSource() throws PropertyVetoException {
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        cpds.setUser(login);
        cpds.setPassword(password);

        cpds.setMinPoolSize(2);
        cpds.setAcquireIncrement(2);
        cpds.setMaxPoolSize(6);
        cpds.setMaxStatementsPerConnection(10000);
        cpds.setMaxStatements(100000);
        cpds.setStatementCacheNumDeferredCloseThreads(1);

        StringBuilder url = new StringBuilder("jdbc:mysql://" + server + ":" + port + "/" + dbName)
                .append("?")
                .append("useSSL=false")
                .append("&")
                .append("useServerPrepStmts=true")
                .append("&")
                .append("rewriteBatchedStatements=true")
                .append("&")
                .append("dontCheckOnDuplicateKeyUpdateInSQL=true")
                .append("&")
                .append("useFastDateParsing=true")
                .append("&")
                .append("maxAllowedPacket=")
                .append(Long.toString(packetSize));

        cpds.setJdbcUrl(url.toString());

        return cpds;
    }
}

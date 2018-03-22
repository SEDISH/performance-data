package org.openmrs.isanteplus.performancedata.model.connection;

import lombok.Getter;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MySqlConnector {

    private String login;

    private String password;

    private String server;

    private String port;

    private String dbName;

    @Getter
    private BasicDataSource dataSource;

    private Connection connection;

    public MySqlConnector(String login, String password, String server, String port,
                          String dbName) {
        this.login = login;
        this.password = password;
        this.server = server;
        this.port = port;
        this.dbName = dbName;
        this.dataSource = setDataSource();
    }

    public void connect() throws SQLException {
        connection = dataSource.getConnection();
    }

    public void disconnect() throws SQLException {
        connection.close();
        connection = null;
    }

    private BasicDataSource setDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + server + ":" + port + "/" + dbName);
        dataSource.setUsername(login);
        dataSource.setPassword(password);
        dataSource.addConnectionProperty("useSSL", "false");
        dataSource.addConnectionProperty("useServerPrepStmts", "false");
        dataSource.addConnectionProperty("rewriteBatchedStatements", "true");

        return dataSource;
    }
}

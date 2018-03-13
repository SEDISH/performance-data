package org.openmrs.isanteplus.performancedata.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlConnector {

    private String login;

    private String password;

    private String server;

    private String port;

    public MySqlConnector(String login, String password, String server, String port) {
        this.login = login;
        this.password = password;
        this.server = server;
        this.port = port;
    }

    public Connection getConnection() throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", login);
        connectionProps.put("password", password);
        connectionProps.put("useSSL", "false");

        return DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/",
                connectionProps);
    }
}

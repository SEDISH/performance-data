package org.openmrs.isanteplus.performancedata.model.connection;

import lombok.Getter;
import org.apache.commons.dbcp.BasicDataSource;

public class MySqlConnector {

    private String login;

    private String password;

    private String server;

    private String port;

    private String dbName;

    private long packetSize;

    @Getter
    private BasicDataSource dataSource;

    public MySqlConnector(String login, String password, String server, String port,
                          String dbName, long packetSize) {
        this.login = login;
        this.password = password;
        this.server = server;
        this.port = port;
        this.dbName = dbName;
        this.packetSize = packetSize;
        this.dataSource = setDataSource();
    }

    private BasicDataSource setDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + server + ":" + port + "/" + dbName);
        dataSource.setUsername(login);
        dataSource.setPassword(password);
        dataSource.addConnectionProperty("useSSL", "false");
        dataSource.addConnectionProperty("useServerPrepStmts", "true");
        dataSource.addConnectionProperty("rewriteBatchedStatements", "true");
        dataSource.addConnectionProperty("dontCheckOnDuplicateKeyUpdateInSQL", "true");
        dataSource.addConnectionProperty("useFastDateParsing", "true");
        dataSource.addConnectionProperty("maxAllowedPacket", Long.toString(packetSize));
        //        dataSource.addConnectionProperty("alwaysSendSetIsolation", "false"); todo

        return dataSource;
    }
}

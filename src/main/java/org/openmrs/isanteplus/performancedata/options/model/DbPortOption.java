package org.openmrs.isanteplus.performancedata.options.model;

import lombok.Getter;
import lombok.Setter;

public class DbPortOption {
    private static final String PORT = "3306";

    @Getter
    @Setter
    private String port;

    public DbPortOption(String port) {
        this.port = port;
    }

    public DbPortOption() {
        this.port = PORT;
    }

    public static String getDefaultPort() {
        return PORT;
    }
}
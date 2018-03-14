package org.openmrs.isanteplus.performancedata.options.model;

import lombok.Getter;
import lombok.Setter;

public class DbServerOption {
    private static final String SERVER = "localhost";

    @Getter
    @Setter
    private String server;

    public DbServerOption(String server) {
        this.server = server;
    }

    public DbServerOption() {
        this.server = SERVER;
    }

    public static String getDefaultServer() {
        return SERVER;
    }
}

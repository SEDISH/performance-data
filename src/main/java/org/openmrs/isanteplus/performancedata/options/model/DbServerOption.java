package org.openmrs.isanteplus.performancedata.options.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class DbServerOption {
    private static final String SERVER = "localhost";

    @Getter
    @Setter
    private String server;

    public DbServerOption() {
        this.server = SERVER;
    }

    public static String getDefaultServer() {
        return SERVER;
    }
}

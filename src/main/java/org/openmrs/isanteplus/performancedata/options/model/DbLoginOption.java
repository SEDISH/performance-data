package org.openmrs.isanteplus.performancedata.options.model;

import lombok.Getter;
import lombok.Setter;

public class DbLoginOption {
    private static final String LOGIN = "root";

    @Getter
    @Setter
    private String login;

    public DbLoginOption(String login) {
        this.login = login;
    }

    public DbLoginOption() {
        this.login = LOGIN;
    }

    public static String getDefaultLogin() {
        return LOGIN;
    }
}

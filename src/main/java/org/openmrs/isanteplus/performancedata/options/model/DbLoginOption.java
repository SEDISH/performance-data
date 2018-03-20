package org.openmrs.isanteplus.performancedata.options.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class DbLoginOption {
    private static final String LOGIN = "root";

    @Getter
    @Setter
    private String login;

    public DbLoginOption() {
        this.login = LOGIN;
    }

    public static String getDefaultLogin() {
        return LOGIN;
    }
}

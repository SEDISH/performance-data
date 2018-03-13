package org.openmrs.isanteplus.performancedata.options.model;

import lombok.Getter;
import lombok.Setter;

public class DbPasswordOption {

    @Getter
    @Setter
    private String password;

    public DbPasswordOption(String password) {
        this.password = password;
    }
}

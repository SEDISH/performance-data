package org.openmrs.isanteplus.performancedata.options.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class PatientOption {
    private static final long PATIENTS = 13_000;

    @Getter
    @Setter
    private long patients;

    public PatientOption() {
        this.patients = PATIENTS;
    }

    public static long getDefaultPatients() {
        return PATIENTS;
    }
}

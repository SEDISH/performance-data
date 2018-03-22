package org.openmrs.isanteplus.performancedata.options.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class PatientOption {
    private static final long PATIENT_NUMBER = 13_000;

    @Getter
    @Setter
    private long patientNumber;

    public PatientOption() {
        this.patientNumber = PATIENT_NUMBER;
    }

    public static long getDefaultPatientNumber() {
        return PATIENT_NUMBER;
    }
}

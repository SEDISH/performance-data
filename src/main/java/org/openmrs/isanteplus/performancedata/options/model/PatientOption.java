package org.openmrs.isanteplus.performancedata.options.model;

public class PatientOption {
    private static final long PATIENTS = 13_000;

    private long patients;

    public PatientOption(long clinics) {
        this.patients = clinics;
    }

    public PatientOption() {
        this.patients = PATIENTS;
    }

    public long getPatients() {
        return patients;
    }

    public void setPatients(long clinics) {
        this.patients = clinics;
    }

    public static long getDefaultPatients() {
        return PATIENTS;
    }
}

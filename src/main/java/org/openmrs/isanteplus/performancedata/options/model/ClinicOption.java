package org.openmrs.isanteplus.performancedata.options.model;

public class ClinicOption {

    private static final long CLINICS = 140;

    private long clinics;

    public ClinicOption(long clinics) {
        this.clinics = clinics;
    }

    public ClinicOption() {
        this.clinics = CLINICS;
    }

    public long getClinics() {
        return clinics;
    }

    public void setClinics(long clinics) {
        this.clinics = clinics;
    }

    public static long getDefaultClinics() {
        return CLINICS;
    }
}

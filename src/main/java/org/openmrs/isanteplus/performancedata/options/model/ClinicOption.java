package org.openmrs.isanteplus.performancedata.options.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ClinicOption {
    private static final long CLINICS = 140;

    @Getter
    @Setter
    private long clinics;

    public ClinicOption() {
        this.clinics = CLINICS;
    }

    public static long getDefaultClinics() {
        return CLINICS;
    }
}

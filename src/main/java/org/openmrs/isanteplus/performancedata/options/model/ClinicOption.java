package org.openmrs.isanteplus.performancedata.options.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ClinicOption {
    private static final long CLINIC_NUMBER = 140;

    @Getter
    @Setter
    private long clinicNumber;

    public ClinicOption() {
        this.clinicNumber = CLINIC_NUMBER;
    }

    public static long getDefaultClinicNumber() {
        return CLINIC_NUMBER;
    }
}

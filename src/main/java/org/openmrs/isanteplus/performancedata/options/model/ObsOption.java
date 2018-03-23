package org.openmrs.isanteplus.performancedata.options.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ObsOption {
    private static final long OBS_NUMBER = 55;

    @Getter
    @Setter
    private long obsNumber;

    public ObsOption() {
        this.obsNumber = OBS_NUMBER;
    }

    public static long getDefaultObsNumber() {
        return OBS_NUMBER;
    }
}

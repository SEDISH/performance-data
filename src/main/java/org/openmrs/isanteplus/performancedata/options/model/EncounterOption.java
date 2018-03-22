package org.openmrs.isanteplus.performancedata.options.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class EncounterOption {
    private static final long ENCOUNTERS = 1;

    @Getter
    @Setter
    private long encountersAmount;

    public EncounterOption() {
        this.encountersAmount = ENCOUNTERS;
    }

    public static long getDefaultEncounters() {
        return ENCOUNTERS;
    }
}

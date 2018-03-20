package org.openmrs.isanteplus.performancedata.options.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class EncounterOption {
    private static final long ENCOUNTERS = 1;

    @Getter
    @Setter
    private long encounters;

    public EncounterOption() {
        this.encounters = ENCOUNTERS;
    }

    public static long getDefaultEncounters() {
        return ENCOUNTERS;
    }
}

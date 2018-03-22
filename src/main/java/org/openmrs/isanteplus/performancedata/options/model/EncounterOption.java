package org.openmrs.isanteplus.performancedata.options.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class EncounterOption {
    private static final long ENCOUNTER_NUMBER = 1;

    @Getter
    @Setter
    private long encounterNumber;

    public EncounterOption() {
        this.encounterNumber = ENCOUNTER_NUMBER;
    }

    public static long getDefaultEncounterNumber() {
        return ENCOUNTER_NUMBER;
    }
}

package org.openmrs.isanteplus.performancedata.generator.predefined;

public enum EncounterTypeEnum {
    VISIT_NOTE(5L);

    private final long id;

    EncounterTypeEnum(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

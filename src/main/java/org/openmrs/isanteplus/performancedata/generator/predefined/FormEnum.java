package org.openmrs.isanteplus.performancedata.generator.predefined;

public enum FormEnum {
    VISIT_NOTE(2L);

    private final long id;

    FormEnum(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

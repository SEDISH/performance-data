package org.openmrs.isanteplus.performancedata.generator.predefined;

public enum ConceptEnum {
    GUARDING(578L),  REBOUND(577L);

    private final long id;

    ConceptEnum(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

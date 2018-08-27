package org.openmrs.isanteplus.performancedata.generator.predefined;

public enum LocationEnum {
    OUTPATIENT_CLINIC(7L), CSL_DE_CABARET(896L);

    private final long id;

    LocationEnum(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

package org.openmrs.isanteplus.performancedata.generator.predefined;

public enum LocationEnum {
    OUTPATIENT_CLINIC(7L);

    private final long id;

    LocationEnum(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

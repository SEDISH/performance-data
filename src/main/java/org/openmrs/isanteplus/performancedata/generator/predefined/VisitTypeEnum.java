package org.openmrs.isanteplus.performancedata.generator.predefined;

public enum VisitTypeEnum {
    FACILITY_VISIT(1L);

    private final long id;

    VisitTypeEnum(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

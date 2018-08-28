package org.openmrs.isanteplus.performancedata.generator.predefined;

public enum ConceptEnum {
    GUARDING(578L),  REBOUND(577L), LABORATORY_RESULTS(1361L), TEST_NAME(162087L), AGE_WHEN_TESTED(163540L),
    AGE_UNIT(163541L), RAPID_TEST_FOR_HIV(163722L);

    private final long id;

    ConceptEnum(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}

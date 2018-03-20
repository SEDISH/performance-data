package org.openmrs.isanteplus.performancedata.generator.predefined;

public enum UserEnum {
    ADMIN(1L),  DAEMON(2L), CLERK(3L), NURSE(3L), DOCTOR(5L), SYS_ADMIN(6L),
    SCHEDULER(7L);

    private final long id;

    UserEnum(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}

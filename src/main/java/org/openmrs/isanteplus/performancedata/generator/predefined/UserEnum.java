package org.openmrs.isanteplus.performancedata.generator.predefined;

public enum UserEnum {
    ADMIN_ID(1L),  DAEMON_ID(2L), CLERK_ID(3L), NURSE_ID(3L), DOCTOR_ID(5L), SYS_ADMIN_ID(6L),
    SCHEDULER_ID(7L);

    private final long id;

    UserEnum(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}

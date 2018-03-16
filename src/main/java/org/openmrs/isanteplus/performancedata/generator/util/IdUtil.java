package org.openmrs.isanteplus.performancedata.generator.util;

public final class IdUtil {

    private static final long INITIAL_ID = 100L;

    private static long personId = INITIAL_ID;

    private static long visitId = INITIAL_ID;

    private static long encounterId = INITIAL_ID;

    private static long obsId = INITIAL_ID;

    public static long getPersonId() {
        return personId++;
    }

    public static long getVisitId() {
        return visitId++;
    }

    public static long getEncounterId() {
        return encounterId++;
    }

    public static long getObsId() {
        return obsId++;
    }

    private IdUtil() {}
}

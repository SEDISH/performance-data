package org.openmrs.isanteplus.performancedata.generator.util;

public final class IdUtil {

    private static final long INITIAL_ID = 100L;

    private static long personId = INITIAL_ID;

    private static long visitId = INITIAL_ID;

    private static long encounterId = INITIAL_ID;

    private static long obsId = INITIAL_ID;

    private static long personAddressId = INITIAL_ID;

    private static long personNameId = INITIAL_ID;

    private static long patientIdentifierId = INITIAL_ID;

    private static long isantePlusID = 12354;

    private static long isanteID = 142027259;

    private static long codeNational = 1531215;

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

    public static long getPersonAddressId() {
        return personAddressId++;
    }

    public static long getPersonNameId() {
        return personNameId++;
    }

    public static long getpatientIdentifierId() {
        return patientIdentifierId++;
    }

    public static String getIsantePlusID() {
        return String.valueOf(isantePlusID++);
    }

    public static String getIsanteID() {
        return String.valueOf(isanteID++);
    }

    public static String getCodeNational() {
        return String.valueOf(codeNational++);
    }

    private IdUtil() {}
}

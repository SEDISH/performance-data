package org.openmrs.isanteplus.performancedata.generator.predefined;

import org.openmrs.isanteplus.performancedata.generator.util.IdUtil;

import java.util.UUID;

public enum PatientIdEnum {
    CODE_NATIONAL(4L), ISANTE_PLUS_ID(5L), ECID(9L), ISANTE_ID(11L);

    private final long id;

    PatientIdEnum(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String generateIdentifier() {
        switch(this) {
            case CODE_NATIONAL:
                return IdUtil.getCodeNational();
            case ISANTE_PLUS_ID:
                return IdUtil.getIsantePlusID();
            case ECID:
                return UUID.randomUUID().toString();
            case ISANTE_ID:
                return IdUtil.getIsanteID();
            default:
                return null;
        }
    }
}

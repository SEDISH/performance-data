package org.openmrs.isanteplus.performancedata.options.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class VisitOption {
    private static final long VISIT_NUMBER = 47;

    @Getter
    @Setter
    private long visitNumber;

    public VisitOption() {
        this.visitNumber = VISIT_NUMBER;
    }

    public static long getDefaultVisitNumber() {
        return VISIT_NUMBER;
    }
}

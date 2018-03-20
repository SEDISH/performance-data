package org.openmrs.isanteplus.performancedata.options.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class VisitOption {
    private static final long VISITS = 47;

    @Getter
    @Setter
    private long visits;

    public VisitOption() {
        this.visits = VISITS;
    }

    public static long getDefaultVisits() {
        return VISITS;
    }
}

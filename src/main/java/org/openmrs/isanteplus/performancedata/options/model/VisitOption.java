package org.openmrs.isanteplus.performancedata.options.model;

public class VisitOption {

    private static final long VISITS = 20;

    private long visits;

    public VisitOption(long visits) {
        this.visits = visits;
    }

    public VisitOption() {
        this.visits = VISITS;
    }

    public long getVisits() {
        return visits;
    }

    public void setVisits(long visits) {
        this.visits = visits;
    }

    public static long getDefaultVisits() {
        return VISITS;
    }
}

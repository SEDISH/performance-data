package org.openmrs.isanteplus.performancedata.options.model;

import java.time.LocalDateTime;

public class StartDateOption {

    private static final LocalDateTime START_DATE = LocalDateTime.of(1970, 1, 1, 0,0);

    private LocalDateTime startDate;

    public StartDateOption(String startDate) {
        this.startDate = LocalDateTime.parse(startDate);
    }

    public StartDateOption() {
        this.startDate = START_DATE;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = LocalDateTime.parse(startDate);
    }

    public static LocalDateTime getDefaultPatients() {
        return START_DATE;
    }
}

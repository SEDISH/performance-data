package org.openmrs.isanteplus.performancedata.generator.util;

import lombok.Getter;
import org.openmrs.isanteplus.performancedata.generator.predefined.UserEnum;

import java.time.LocalDateTime;

public class ParamData {

    private boolean param;

    @Getter
    private Long paramBy;

    @Getter
    private LocalDateTime dateParam;

    @Getter
    private String paramReason;

    public ParamData() {
        param = false;
        paramBy = null;
        dateParam = null;
        paramReason = null;
    }

    public ParamData(LocalDateTime start) {
        param = true;
        paramBy = UserEnum.ADMIN.getId();
        dateParam = RandUtil.getDateFromStartToNow(start);
        paramReason = RandUtil.getString();
    }

    public long getParam() {
        return param ? 1L : 0L;
    }
}

package org.openmrs.isanteplus.performancedata.generator.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class RandUtil {

    private static final ZoneOffset offset = ZoneOffset.ofHours(1);
    private static final int nanoSec = 0;

    public static String getString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }

    public static String get3Characters() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "").substring(0,2);
    }

    public static boolean getBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public static LocalDateTime getLocalDateTime(LocalDateTime start) {
        LocalDateTime minDate = Optional.ofNullable(start)
                .orElse(LocalDateTime.of(1970, 1, 1, 0,0));

        long minDay = minDate.toEpochSecond(offset);
        long maxDay = LocalDateTime.now().toEpochSecond(offset);
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);

        return LocalDateTime.ofEpochSecond(randomDay, nanoSec, offset);
    }

    private RandUtil(){
    }
}

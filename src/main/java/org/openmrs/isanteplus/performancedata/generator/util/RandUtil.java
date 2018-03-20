package org.openmrs.isanteplus.performancedata.generator.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class RandUtil {

    private static final ZoneOffset offset = ZoneOffset.ofHours(0);

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

    public static LocalDateTime getDateFromStartToNow(LocalDateTime start) {
        return getLocalDateTime(start, LocalDateTime.now());
    }

    public static LocalDateTime getDateFromStartToNextDay(LocalDateTime startDate) {
        return getLocalDateTime(startDate, startDate.plusDays(1));
    }

    public static LocalDateTime getDateFromStartToEnd(LocalDateTime startDate,
                                                      LocalDateTime endDate) {
        return getLocalDateTime(startDate, endDate);
    }

    private static LocalDateTime getLocalDateTime(LocalDateTime startDate, LocalDateTime endDate) {
        Instant min = startDate.toInstant(offset);
        Instant max = endDate.toInstant(offset);

        long randomDay = ThreadLocalRandom.current().nextLong(min.toEpochMilli(),
                max.toEpochMilli());

        return LocalDateTime.ofInstant(Instant.ofEpochMilli(randomDay), offset);
    }

    private RandUtil(){
    }
}

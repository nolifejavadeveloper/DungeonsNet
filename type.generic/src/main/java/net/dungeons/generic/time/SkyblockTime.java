package net.dungeons.generic.time;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class SkyblockTime {
    @Getter
    private static long year;
    @Getter
    private static int month;
    @Getter
    private static int day;
    @Getter
    private static int hour;
    @Getter
    private static int minute;

    private static final double REAL_SECONDS_PER_SKYBLOCK_MINUTE = 50.0 / 60.0;

    // Skyblock constants
    private static final int SKYBLOCK_MINUTES_IN_HOUR = 60;
    private static final int SKYBLOCK_HOURS_IN_DAY = 24;
    private static final int SKYBLOCK_DAYS_IN_MONTH = 31;
    private static final int SKYBLOCK_MONTHS_IN_YEAR = 12;

    // Skyblock starting epoch (arbitrary date)
    private static final LocalDateTime SKYBLOCK_START_DATE = LocalDateTime.of(2019, 6, 11, 17, 55);

    private static void update() {
        LocalDateTime realTime = LocalDateTime.now(ZoneId.of("GMT"));
        long secondsSinceEpoch = java.time.Duration.between(SKYBLOCK_START_DATE, realTime).getSeconds();
        long totalSkyblockMinutes = (long) (secondsSinceEpoch / REAL_SECONDS_PER_SKYBLOCK_MINUTE);

        // Calculate Skyblock time components
        long skyblockYears = totalSkyblockMinutes / (SKYBLOCK_DAYS_IN_MONTH * SKYBLOCK_MONTHS_IN_YEAR * SKYBLOCK_HOURS_IN_DAY * SKYBLOCK_MINUTES_IN_HOUR);
        long remainingMinutes = totalSkyblockMinutes % (SKYBLOCK_DAYS_IN_MONTH * SKYBLOCK_MONTHS_IN_YEAR * SKYBLOCK_HOURS_IN_DAY * SKYBLOCK_MINUTES_IN_HOUR);

        int skyblockMonths = (int) (remainingMinutes / (SKYBLOCK_DAYS_IN_MONTH * SKYBLOCK_HOURS_IN_DAY * SKYBLOCK_MINUTES_IN_HOUR));
        remainingMinutes %= (SKYBLOCK_DAYS_IN_MONTH * SKYBLOCK_HOURS_IN_DAY * SKYBLOCK_MINUTES_IN_HOUR);

        int skyblockDays = (int) (remainingMinutes / (SKYBLOCK_HOURS_IN_DAY * SKYBLOCK_MINUTES_IN_HOUR));
        remainingMinutes %= (SKYBLOCK_HOURS_IN_DAY * SKYBLOCK_MINUTES_IN_HOUR);

        int skyblockHours = (int) (remainingMinutes / SKYBLOCK_MINUTES_IN_HOUR);
        int skyblockMinutes = (int) (remainingMinutes % SKYBLOCK_MINUTES_IN_HOUR);

        // Round minutes to the nearest 10
        skyblockMinutes = (int) (Math.round(skyblockMinutes / 10.0) * 10);
        if (skyblockMinutes == 60) {
            skyblockMinutes = 0;
            skyblockHours++;
        }

        skyblockYears++;
        skyblockMonths++;
        skyblockDays++;

        year = skyblockYears;
        month = skyblockMonths;
        day = skyblockDays;
        hour = skyblockHours;
        minute = skyblockMinutes;
    }

}

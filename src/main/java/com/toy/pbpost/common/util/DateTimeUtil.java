package com.toy.pbpost.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtil {

    private static final String DEFAULT_TIMEZONE = "Asia/Seoul";

    public static ZonedDateTime saveUTCDate(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.of(DEFAULT_TIMEZONE)).withZoneSameInstant(ZoneId.of("Z"));
    }

    public static ZonedDateTime saveZoneDateTime(LocalDateTime localDateTime, String timezone) {
        return localDateTime.atZone(ZoneId.of(timezone));
    }

    public static ZonedDateTime getZoneDateTime(LocalDateTime localDateTime, String timezone) {
        return getZoneDateTime(localDateTime, timezone, "Z");
    }

    public static ZonedDateTime getZoneDateTime(LocalDateTime localDateTime, String timezone, String beforeTimezone) {
        return localDateTime.atZone(ZoneId.of(beforeTimezone)).withZoneSameInstant(ZoneId.of(timezone));
    }


}

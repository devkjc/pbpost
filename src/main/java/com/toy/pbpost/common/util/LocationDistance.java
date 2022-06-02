package com.toy.pbpost.common.util;

import com.toy.pbpost.common.domain.LengthUnit;
import com.toy.pbpost.common.domain.TimeDto;

import java.time.ZonedDateTime;

public class LocationDistance {


    public ZonedDateTime requiredTime(double lat1, double lon1, double lat2, double lon2) {

        return null;
    }

    /**
     * 두 지점간의 거리 계산
     *
     * @param lat1 지점 1 위도
     * @param lon1 지점 1 경도
     * @param lat2 지점 2 위도
     * @param lon2 지점 2 경도
     * @param unit 거리 표출단위
     * @return
     */
    private double distance(double lat1, double lon1, double lat2, double lon2, LengthUnit unit) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if (unit.equals(LengthUnit.KILOMETER)) {
            dist = dist * 1.609344;
        } else if (unit.equals(LengthUnit.METER)) {
            dist = dist * 1609.344;
        }

        return (dist);
    }

    private TimeDto requiredTime(double distance, double perHour) {

        int day = 0;
        int hour =  (int) ( distance / perHour);
        int min =  (int) ((( distance / perHour) - hour) * 60);
        int sec =  (int) ((((( distance / perHour) - hour) * 60) - min) * 60);

        if (hour > 24) {
            day = hour / 24;
            hour = hour % 24;
        }

        return TimeDto.builder()
                .day(day)
                .hour(hour)
                .min(min)
                .sec(sec)
                .build();
    }

    // This function converts decimal degrees to radians
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}


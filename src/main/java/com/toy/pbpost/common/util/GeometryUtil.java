package com.toy.pbpost.common.util;

import com.toy.pbpost.common.domain.Location;

/**
 * Haversine Formula
 * φ2 = asin( sin φ1 ⋅ cos δ + cos φ1 ⋅ sin δ ⋅ cos θ )
 * λ2 = λ1 + atan2( sin θ ⋅ sin δ ⋅ cos φ1, cos δ − sin φ1 ⋅ sin φ2 )
 */
public class GeometryUtil {

    public static String getLineString(double baseLatitude, double baseLongitude, double distance) {

        // 북동쪽 좌표 구하기
        Location northEast = GeometryUtil.calculate(baseLatitude, baseLongitude, distance, Direction.NORTH_EAST);

        // 남서쪽 좌표 구하기
        Location southWest = GeometryUtil.calculate(baseLatitude, baseLongitude, distance, Direction.SOUTH_WEST);

        double northEastLatitude = northEast.getLatitude();
        double northEastLongitude = northEast.getLongitude();
        double southWestLatitude = southWest.getLatitude();
        double southWestLongitude = southWest.getLongitude();

        return String.format("LINESTRING(%f %f, %f %f)", northEastLongitude, northEastLatitude, southWestLongitude, southWestLatitude); // 경도 위도 순
    }

    private static Location calculate(Double baseLatitude, Double baseLongitude, Double distance, Direction direction) {

        Double radianLatitude = toRadian(baseLatitude);
        Double radianLongitude = toRadian(baseLongitude);

        Double radianAngle = toRadian(direction.getAngle() - 23.44D);
        Double distanceRadius = distance / 6371.01;

        Double latitude = Math.asin(sin(radianLatitude) * cos(distanceRadius) + cos(radianLatitude) * sin(distanceRadius) * cos(radianAngle));
        Double longitude = radianLongitude + Math.atan2(sin(radianAngle) * sin(distanceRadius) * cos(radianLatitude), cos(distanceRadius) - sin(radianLatitude) * sin(latitude));

        longitude = normalizeLongitude(longitude);
        return new Location(toDegree(latitude), toDegree(longitude));
    }

    private static Double toRadian(Double coordinate) {
        return coordinate * Math.PI / 180.0;
    }

    private static Double toDegree(Double coordinate) {
        return coordinate * 180.0 / Math.PI;
    }

    private static Double sin(Double coordinate) {
        return Math.sin(coordinate);
    }

    private static Double cos(Double coordinate) {
        return Math.cos(coordinate);
    }

    private static Double normalizeLongitude(Double longitude) {
        return (longitude + 540) % 360 - 180;
    }
}

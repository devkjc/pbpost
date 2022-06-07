package com.toy.pbpost.common.domain;

import lombok.*;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@Embeddable
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String address;

    // 위도
    @Column(precision = 10, scale = 7)
    private BigDecimal latitude;

    // 경도
    @Column(precision = 10, scale = 7)
    private BigDecimal longitude;

    @Column
    private Point locationPoint;

    public static Address createAddress(BigDecimal latitude, BigDecimal longitude, String address) {
        try {
            String pointWKT = String.format("POINT(%s %s)", longitude, latitude); // 경도 위도 순
            Point point = (Point) new WKTReader().read(pointWKT);
            return Address.builder().latitude(latitude).longitude(longitude).locationPoint(point).address(address).build();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setLocationPoint(Point locationPoint) {
        this.locationPoint = locationPoint;
    }
}

package com.toy.pbpost.common.domain;

import lombok.*;
import org.locationtech.jts.geom.Point;

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

    public void setLocationPoint(Point locationPoint) {
        this.locationPoint = locationPoint;
    }
}

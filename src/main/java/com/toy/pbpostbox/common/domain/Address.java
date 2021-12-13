package com.toy.pbpostbox.common.domain;

import lombok.*;

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

    private String address1;
    private String address2;

    @Column(precision = 10, scale = 7)
    private BigDecimal longitude;

    @Column(precision = 10, scale = 7)
    private BigDecimal latitude;
}

package com.toy.pbpost.common.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class TimeDto {

    int day;
    int hour;
    int min;
    int sec;
    double distance;
    LengthUnit unit;

}

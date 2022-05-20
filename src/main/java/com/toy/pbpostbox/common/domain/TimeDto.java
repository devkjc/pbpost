package com.toy.pbpostbox.common.domain;

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

}

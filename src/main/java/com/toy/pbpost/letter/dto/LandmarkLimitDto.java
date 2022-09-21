package com.toy.pbpost.letter.dto;

import com.toy.pbpost.postbox.domain.LandmarkLimit;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

public class LandmarkLimitDto {

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "LandmarkLimitDto.Res")
    public static class Res {

        private Long id;
        private String uid;
        private LocalDate date;
        private String timezone;
        private Long count;

        public static Res of(LandmarkLimit landmarkLimit) {
            return landmarkLimit != null ?
                    Res.builder()
                            .id(landmarkLimit.getId())
                            .uid(landmarkLimit.getUid())
                            .date(landmarkLimit.getDate())
                            .timezone(landmarkLimit.getTimezone())
                            .count(landmarkLimit.getCount())
                            .build() : null;
        }
    }


}

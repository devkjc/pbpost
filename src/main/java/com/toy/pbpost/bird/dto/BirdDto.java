package com.toy.pbpost.bird.dto;

import com.toy.pbpost.bird.domain.Bird;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class BirdDto {

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "BirdDto.Res")
    public static class Res {

        private long id;
        private String uid;
        private String birdName;
        private Double hourly;

        public static Res of(Bird bird) {

            if (bird == null) {
                return Res.builder()
                        .birdName("탈퇴회원")
                        .build();
            }

            return Res.builder()
                    .id(bird.getId())
                    .uid(bird.getUid())
                    .birdName(bird.getBirdName())
                    .hourly(bird.getHourly())
                    .build();
        }
    }

}

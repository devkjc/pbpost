package com.toy.pbpostbox.postbox.dto;

import com.toy.pbpostbox.postbox.domain.Landmark;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class LandmarkDto {

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "LandmarkDto.Res")
    public static class Res {

        private long id;
        private String name;
        private String engName;
        private AddressDto.Res address;

        public static Res of(Landmark landmark) {

            return Res.builder()
                    .id(landmark.getId())
                    .name(landmark.getName())
                    .engName(landmark.getEngName())
                    .address(AddressDto.Res.of(landmark.getAddress()))
                    .build();
        }
    }

}

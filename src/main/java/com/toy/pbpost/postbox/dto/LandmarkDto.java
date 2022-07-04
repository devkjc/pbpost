package com.toy.pbpost.postbox.dto;

import com.toy.pbpost.letter.dto.LetterDto;
import com.toy.pbpost.postbox.domain.Landmark;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

public class LandmarkDto {

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "LandmarkDto.SimpleRes")
    public static class SimpleRes {

        private long id;
        private String name;
        private String engName;
        private AddressDto.Res address;

        public static SimpleRes of(Landmark landmark) {

            return SimpleRes.builder()
                    .id(landmark.getId())
                    .name(landmark.getName())
                    .engName(landmark.getEngName())
                    .address(AddressDto.Res.of(landmark.getAddress()))
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "LandmarkDto.Res")
    public static class Res {

        private long id;
        private String name;
        private String engName;
        private AddressDto.Res address;
        private List<LetterDto.SimpleRes> letterList;

        public static Res of(Landmark landmark, String timezone) {

            return Res.builder()
                    .id(landmark.getId())
                    .name(landmark.getName())
                    .engName(landmark.getEngName())
                    .address(AddressDto.Res.of(landmark.getAddress()))
                    .letterList(landmark.getLetterList().stream().map(letter -> LetterDto.SimpleRes.of(letter, timezone)).collect(Collectors.toList()))
                    .build();
        }
    }

}

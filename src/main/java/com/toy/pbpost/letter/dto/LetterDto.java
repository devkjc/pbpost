package com.toy.pbpost.letter.dto;

import com.toy.pbpost.bird.dto.BirdDto;
import com.toy.pbpost.letter.domain.Letter;
import com.toy.pbpost.letter.domain.LetterBackground;
import com.toy.pbpost.letter.domain.LetterFont;
import com.toy.pbpost.postbox.dto.AddressDto;
import com.toy.pbpost.postbox.dto.LandmarkDto;
import com.toy.pbpost.postbox.dto.PostBoxDto;
import com.toy.pbpost.user.dto.UserDto;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class LetterDto {

    @Getter
    @ToString
    @ApiModel(value = "LetterDto.Req")
    public static class Req {

        @NotNull
        private BigDecimal latitude;

        @NotNull
        private BigDecimal longitude;

        private Long toLandmarkId;

        private Long toPostBoxId;

        @NotNull
        private Long birdId;

        @NotNull
        private Long fontId;

        @NotNull
        private Long backgroundId;

        @NotNull
        private String content;

    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "LetterDto.Res")
    public static class Res {

        private long id;
        private UserDto.Res from;
        private UserDto.Res to;
        private BirdDto.Res bird;
        private LandmarkDto.SimpleRes toLandmark;
        private PostBoxDto.Res toPostBox;
        private PostBoxDto.Res returnPostBox;
        private LetterFont font;
        private LetterBackground background;
        private AddressDto.Res departureAddress;
        private ZonedDateTime departureTime;
        private ZonedDateTime arrivalTime;
        private ZonedDateTime returnTime;
        private String content;
        private Boolean enabled;

        public static Res of(Letter letter, String timezone) {

            return Res.builder()
                    .id(letter.getId())
                    .from(UserDto.Res.of(letter.getFrom()))
                    .to(letter.getTo() != null ? UserDto.Res.of(letter.getTo()) : null)
                    .bird(BirdDto.Res.of(letter.getBird()))
                    .departureAddress(AddressDto.Res.of(letter.getDepartureAddress()))
                    .toLandmark(letter.getToLandmark() != null ? LandmarkDto.SimpleRes.of(letter.getToLandmark()) : null)
                    .toPostBox(letter.getToPostBox() != null ? PostBoxDto.Res.of(letter.getToPostBox()) : null)
                    .returnPostBox(letter.getReturnPostBox() != null ? PostBoxDto.Res.of(letter.getReturnPostBox()) : null)
                    .font(letter.getFont())
                    .background(letter.getBackground())
                    .departureTime(getZoneDateTime(letter.getDepartureTime(),timezone))
                    .arrivalTime(getZoneDateTime(letter.getArrivalTime(), timezone))
                    .returnTime(getZoneDateTime(letter.getReturnTime(), timezone))
                    .content(letter.getContent())
                    .enabled(letter.getEnabled())
                    .build();
        }


    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "LetterDto.SimpleRes")
    public static class SimpleRes {

        private long id;
        private UserDto.Res from;
        private UserDto.Res to;
        private AddressDto.Res departureAddress;
        private ZonedDateTime arrivalTime;
        private Boolean enabled;

        public static SimpleRes of(Letter letter, String timezone) {

            return LetterDto.SimpleRes.builder()
                    .id(letter.getId())
                    .from(UserDto.Res.of(letter.getFrom()))
                    .to(letter.getTo() != null ? UserDto.Res.of(letter.getTo()) : null)
                    .departureAddress(AddressDto.Res.of(letter.getDepartureAddress()))
                    .arrivalTime(getZoneDateTime(letter.getArrivalTime(), timezone))
                    .enabled(letter.getEnabled())
                    .build();
        }
    }

    public static ZonedDateTime getZoneDateTime(LocalDateTime localDateTime, String timezone) {
        return localDateTime.atZone(ZoneId.of("Z")).withZoneSameInstant(ZoneId.of(timezone));
    }

}

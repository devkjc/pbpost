package com.toy.pbpost.letter.dto;

import com.toy.pbpost.bird.dto.BirdDto;
import com.toy.pbpost.letter.domain.Letter;
import com.toy.pbpost.letter.domain.LetterBackground;
import com.toy.pbpost.letter.domain.LetterFont;
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
        private LandmarkDto.Res toLandmark;
        private PostBoxDto.Res toPostBox;
        private LetterFont font;
        private LetterBackground background;
        private LocalDateTime departureTime;
        private LocalDateTime arrivalTime;
        private String content;

        public static Res of(Letter letter) {

            return Res.builder()
                    .id(letter.getId())
                    .from(UserDto.Res.of(letter.getFrom()))
                    .to(letter.getTo() != null ? UserDto.Res.of(letter.getTo()) : null)
                    .bird(BirdDto.Res.of(letter.getBird()))
                    .toLandmark(letter.getToLandmark() != null ? LandmarkDto.Res.of(letter.getToLandmark()) : null)
                    .toPostBox(letter.getToPostBox() != null ? PostBoxDto.Res.of(letter.getToPostBox()) : null)
                    .font(letter.getFont())
                    .background(letter.getBackground())
                    .departureTime(letter.getDepartureTime())
                    .arrivalTime(letter.getArrivalTime())
                    .content(letter.getContent())
                    .build();
        }
    }

}

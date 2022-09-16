package com.toy.pbpost.letter.dto;

import com.toy.pbpost.letter.domain.LetterBox;
import com.toy.pbpost.user.dto.UserDto;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

public class LetterBoxDto {

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "LetterBoxDto.Res")
    public static class Res {

        private Long id;
        private UserDto.Res user;
        private List<LetterDto.Res> letterList;

        public static Res of(LetterBox letterBox) {

            return letterBox != null ?
                    Res.builder()
                            .id(letterBox.getId())
                            .user(UserDto.Res.of(letterBox.getUser()))
                            .letterList(letterBox.getLetterList().stream().map(letter -> LetterDto.Res.of(letter, letterBox.getUser().getTimezone())).collect(Collectors.toList()))
                            .build() : null;
        }
    }


}

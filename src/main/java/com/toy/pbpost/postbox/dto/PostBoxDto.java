package com.toy.pbpost.postbox.dto;

import com.toy.pbpost.common.domain.Address;
import com.toy.pbpost.letter.dto.LetterDto;
import com.toy.pbpost.postbox.domain.PostBox;
import com.toy.pbpost.user.domain.User;
import com.toy.pbpost.user.dto.UserDto;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class PostBoxDto {

    @Getter
    @ToString
    @ApiModel(value = "PostBoxDto.Req")
    public static class Req {

        private String address;

        // 위도
        @NotNull
        private BigDecimal latitude;

        // 경도
        @NotNull
        private BigDecimal longitude;

        public PostBox toEntity(User user) {
            return PostBox.builder()
                    .user(user)
                    .address(Address.createAddress(latitude, longitude, address))
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "PostBoxDto.Res")
    public static class Res {

        private long id;
        private UserDto.Res user;
        private AddressDto.Res address;
        private List<LetterDto.SimpleRes> letterList;

        public static Res of(PostBox postBox, String timezone) {

            if (postBox == null) {
                return null;
            }

            return Res.builder()
                    .id(postBox.getId())
                    .user(UserDto.Res.of(postBox.getUser()))
                    .address(AddressDto.Res.of(postBox.getAddress()))
                    .letterList(postBox.getLetterList().stream().map(letter -> LetterDto.SimpleRes.of(letter, timezone, postBox.getAddress())).collect(Collectors.toList()))
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "PostBoxDto.SimpleRes")
    public static class SimpleRes {

        private long id;
        private UserDto.Res user;
        private AddressDto.Res address;

        public static SimpleRes of(PostBox postBox) {
            return SimpleRes.builder()
                    .id(postBox.getId())
                    .user(UserDto.Res.of(postBox.getUser()))
                    .address(AddressDto.Res.of(postBox.getAddress()))
                    .build();
        }
    }

}

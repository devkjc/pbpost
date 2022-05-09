package com.toy.pbpostbox.user.dto;

import com.toy.pbpostbox.user.domain.User;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class UserDto {

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "UserDto.Res")
    public static class Res {

        private final String uid;
        private final String nickName;
        private final String code;

        public static Res of(User user) {
            return Res.builder()
                    .uid(user.getUid())
                    .nickName(user.getNickName())
                    .code(user.getCode())
                    .build();
        }
    }
}

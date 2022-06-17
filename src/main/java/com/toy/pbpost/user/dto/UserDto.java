package com.toy.pbpost.user.dto;

import com.toy.pbpost.user.domain.User;
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

            if (user == null) {
                return Res.builder()
                        .uid("")
                        .code("")
                        .nickName("탈퇴회원")
                        .build();
            }else{
                return Res.builder()
                        .uid(user.getUid())
                        .nickName(user.getNickName())
                        .code(user.getCode())
                        .build();
            }

        }
    }
}

package com.toy.pbpostbox.postbox.dto;

import com.toy.pbpostbox.common.domain.Address;
import com.toy.pbpostbox.postbox.domain.PostBox;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PostBoxDto {

    @Getter
    @ToString
    @ApiModel(value = "PostBoxDto.Req")
    public static class Req {

        private String address1;
        private String address2;
        @NotNull
        private BigDecimal longitude;
        @NotNull
        private BigDecimal latitude;

        public PostBox toEntity(String uId) {
            return PostBox.builder()
                    .uid(uId)
                    .address(Address.builder().address1(address1).address2(address2).latitude(latitude).longitude(longitude).build())
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "PostBoxDto.Res")
    public static class Res {

        private long id;
        private Address address;

        public static Res of(PostBox postBox) {
            return Res.builder()
                    .id(postBox.getId())
                    .address(postBox.getAddress())
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "PostBoxDto.SimpleRes")
    public static class SimpleRes {

        private long id;
        private Address address;

        public static SimpleRes of(PostBox postBox) {
            return SimpleRes.builder()
                    .id(postBox.getId())
                    .address(postBox.getAddress())
                    .build();
        }
    }

}

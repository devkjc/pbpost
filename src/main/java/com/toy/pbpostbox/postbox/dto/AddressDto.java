package com.toy.pbpostbox.postbox.dto;

import com.toy.pbpostbox.common.domain.Address;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

public class AddressDto {

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "AddressDto.Res")
    public static class Res {

        private String address;
        private BigDecimal latitude;
        private BigDecimal longitude;

        public static Res of(Address address) {

            return Res.builder()
                    .address(address.getAddress())
                    .latitude(address.getLatitude())
                    .longitude(address.getLongitude())
                    .build();
        }
    }

}

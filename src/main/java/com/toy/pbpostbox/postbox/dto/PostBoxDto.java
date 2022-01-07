package com.toy.pbpostbox.postbox.dto;

import com.toy.pbpostbox.common.domain.Address;
import com.toy.pbpostbox.postbox.domain.PostBox;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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

        public PostBox toEntity(String uId) {
            try {

                String pointWKT = String.format("POINT(%s %s)", latitude, longitude);
                Point point = (Point) new WKTReader().read(pointWKT);

                return PostBox.builder()
                        .uid(uId)
                        .address(Address.builder().address(address).latitude(latitude).longitude(longitude).locationPoint(point).build())
                        .build();

            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "PostBoxDto.Res")
    public static class Res {

        private long id;
        private AddressDto.Res address;

        public static Res of(PostBox postBox) {
            return Res.builder()
                    .id(postBox.getId())
                    .address(AddressDto.Res.of(postBox.getAddress()))
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "PostBoxDto.SimpleRes")
    public static class SimpleRes {

        private long id;
        private AddressDto.Res address;

        public static SimpleRes of(PostBox postBox) {
            return SimpleRes.builder()
                    .id(postBox.getId())
                    .address(AddressDto.Res.of(postBox.getAddress()))
                    .build();
        }
    }

}

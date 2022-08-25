package com.toy.pbpost.addressBook.dto;

import com.toy.pbpost.addressBook.domain.AddressBook;
import com.toy.pbpost.postbox.dto.PostBoxDto;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class AddressBookDto {

    @Getter
    @ToString
    @ApiModel(value = "AddressBookDto.Req")
    public static class Req {

        public AddressBook toEntity() {
            return AddressBook.builder()
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "AddressBookDto.Res")
    public static class Res {

        private long id;
        private PostBoxDto.SimpleRes postBox;

        public static Res of(AddressBook addressBook) {

            return Res.builder()
                    .id(addressBook.getId())
                    .postBox(PostBoxDto.SimpleRes.of(addressBook.getPostBox()))
                    .build();
        }
    }

}

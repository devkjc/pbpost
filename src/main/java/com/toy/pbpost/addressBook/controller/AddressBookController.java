package com.toy.pbpost.addressBook.controller;

import com.toy.pbpost.addressBook.dto.AddressBookDto;
import com.toy.pbpost.addressBook.service.AddressBookService;
import com.toy.pbpost.config.security.SecurityService;
import com.toy.pbpost.postbox.dto.PostBoxDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addressBook")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
@Api(tags = {"AddressBook API"})
public class AddressBookController {

    private final AddressBookService addressBookService;

    @GetMapping
    @ApiOperation(value = "주소록 조회")
    public ResponseEntity<List<AddressBookDto.Res>> getMyAddressBook() {
        String uid = SecurityService.getUid();
        return ResponseEntity.ok(addressBookService.getMyAddressBook(uid));
    }

    @PostMapping("/code")
    @ApiOperation(value = "유저 코드로 주소록 등록")
    public ResponseEntity<AddressBookDto.Res> saveAddressBook(@RequestBody String code) {
        String uid = SecurityService.getUid();
        return ResponseEntity.ok(addressBookService.saveAddressBookByCode(uid, code));
    }

    @PostMapping("/nickname")
    @ApiOperation(value = "유저 닉네임으로 주소록 등록")
    public ResponseEntity<AddressBookDto.Res> saveAddressBookWithNickname(@RequestBody String nickname) {
        String uid = SecurityService.getUid();
        return ResponseEntity.ok(addressBookService.saveAddressBookByNickname(uid, nickname));
    }

    @DeleteMapping("/{addressBookId}")
    @ApiOperation(value = "주소록 삭제")
    public ResponseEntity<?> deleteAddressBook(@PathVariable long addressBookId) {
        String uid = SecurityService.getUid();
        addressBookService.deleteAddressBook(uid, addressBookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/map/{lat}/{lon}/{distance}")
    @ApiOperation(value = "내 주소록에 있는 주변 우체통 조회")
    public ResponseEntity<List<PostBoxDto.SimpleRes>> getSquareMapPostBoxList(
            @ApiParam(value = "위도") @PathVariable Double lat,
            @ApiParam(value = "경도") @PathVariable Double lon,
            @ApiParam(value = "거리(km)") @PathVariable Double distance
    ) {
        String uid = SecurityService.getUid();
        return ResponseEntity.ok(addressBookService.getSquareMapPostBoxList(lat, lon, distance, uid));
    }

}

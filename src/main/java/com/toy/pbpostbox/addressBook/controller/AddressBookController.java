package com.toy.pbpostbox.addressBook.controller;

import com.toy.pbpostbox.addressBook.dto.AddressBookDto;
import com.toy.pbpostbox.addressBook.service.AddressBookService;
import com.toy.pbpostbox.config.security.SecurityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @DeleteMapping("/{addressBookId}")
    @ApiOperation(value = "주소록 삭제")
    public ResponseEntity<?> deleteAddressBook(@PathVariable long addressBookId) {
        String uid = SecurityService.getUid();
        addressBookService.deleteAddressBook(uid, addressBookId);
        return ResponseEntity.ok().build();
    }

}

package com.toy.pbpost.letter.controller;

import com.toy.pbpost.config.security.SecurityService;
import com.toy.pbpost.letter.dto.LetterBoxDto;
import com.toy.pbpost.letter.service.LetterBoxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/letterBox")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
@Api(tags = {"LetterBox API"})
public class LetterBoxController {

    private final LetterBoxService letterBoxService;

    @GetMapping
    @ApiOperation(value = "내 편지 보관함 조회")
    public ResponseEntity<LetterBoxDto.Res> getLetterBox() {
        String uid = SecurityService.getUid();
        return ResponseEntity.ok(letterBoxService.getLetterBoxDto(uid));
    }

}

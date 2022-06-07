package com.toy.pbpost.letter.controller;

import com.toy.pbpost.config.security.SecurityService;
import com.toy.pbpost.letter.dto.LetterDto;
import com.toy.pbpost.letter.service.LetterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/letter")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
@Api(tags = {"Letter API"})
public class LetterController {

    private final LetterService letterService;

    @PostMapping
    @ApiOperation(value = "편지 쓰기")
    public ResponseEntity<LetterDto.Res> saveLetter(@RequestBody LetterDto.Req req) {
        String uid = SecurityService.getUid();
        return ResponseEntity.ok(letterService.saveLetter(uid, req));
    }

}

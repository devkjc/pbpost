package com.toy.pbpost.letter.controller;

import com.toy.pbpost.common.domain.TimeDto;
import com.toy.pbpost.config.security.SecurityService;
import com.toy.pbpost.letter.dto.LetterDto;
import com.toy.pbpost.letter.service.LetterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/send/list")
    @ApiOperation(value = "현재 전송 중인 편지 정보 확인")
    public ResponseEntity<List<LetterDto.Res>> sendLetterList() {
        String uid = SecurityService.getUid();
        return ResponseEntity.ok(letterService.sendLetterList(uid));
    }

    @DeleteMapping("/{letterId}")
    @ApiOperation(value = "편지 삭제 - 테스트용")
    public ResponseEntity<?> deleteLetter(@PathVariable long letterId) {
        String uid = SecurityService.getUid();
        letterService.deleteLetter(uid, letterId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/time/{lat1}/{lon1}/{lat2}/{lon2}/{birdId}")
    @ApiOperation(value = "특정장소까지 거리 및 예상 소요시간 조회")
    public ResponseEntity<TimeDto> getRequiredTime(@PathVariable double lat1, @PathVariable double lon1,
                                                   @PathVariable double lat2, @PathVariable double lon2,
                                                   @PathVariable long birdId) {
        return ResponseEntity.ok(letterService.getRequiredTime(lat1, lon1, lat2, lon2, birdId));
    }

}

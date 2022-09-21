package com.toy.pbpost.postbox.controller;

import com.toy.pbpost.config.security.SecurityService;
import com.toy.pbpost.letter.dto.LandmarkLimitDto;
import com.toy.pbpost.postbox.dto.LandmarkDto;
import com.toy.pbpost.postbox.service.LandmarkLimitService;
import com.toy.pbpost.postbox.service.LandmarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/landmark")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
@Api(tags = {"Landmark API"})
public class LandmarkController {

    private final LandmarkService landmarkService;
    private final LandmarkLimitService landmarkLimitService;

    @GetMapping("/map/{lat}/{lon}/{distance}")
    @ApiOperation(value = "내 주변 랜드마크 조회")
    public ResponseEntity<List<LandmarkDto.SimpleRes>> getSquareMapLandmarkList(
            @ApiParam(value = "위도") @PathVariable Double lat,
            @ApiParam(value = "경도") @PathVariable Double lon,
            @ApiParam(value = "거리(km)") @PathVariable Double distance) {
        return ResponseEntity.ok(landmarkService.getSquareMapLandmarkList(lat, lon, distance));
    }

    @GetMapping
    @ApiOperation(value = "랜드마크 전체 조회")
    public ResponseEntity<List<LandmarkDto.SimpleRes>> getLandmarkList() {
        return ResponseEntity.ok(landmarkService.getLandmarkList());
    }

    @GetMapping("/{landmarkId}")
    @ApiOperation(value = "랜드마크 자세히 보기")
    public ResponseEntity<LandmarkDto.Res> getLandmark(@PathVariable long landmarkId) {
        String uid = SecurityService.getUid();
        return ResponseEntity.ok(landmarkService.getLandmark(landmarkId, uid));
    }

    @GetMapping("/letter/limit")
    @ApiOperation(value = "오늘 랜드마크 편지 수령 횟수 조회")
    public ResponseEntity<LandmarkLimitDto.Res> getLandmarkLimit() {
        String uid = SecurityService.getUid();
        return ResponseEntity.ok(landmarkLimitService.getLandmarkLimit(uid));
    }

}

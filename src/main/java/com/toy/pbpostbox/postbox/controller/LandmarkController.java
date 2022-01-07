package com.toy.pbpostbox.postbox.controller;

import com.toy.pbpostbox.postbox.dto.LandmarkDto;
import com.toy.pbpostbox.postbox.service.LandmarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = {"랜드마크"})
public class LandmarkController {

    private final LandmarkService landmarkService;

    @GetMapping("/map/{lat}/{lot}/{distance}")
    @ApiOperation(value = "내 주변 랜드마크 조회")
    public ResponseEntity<List<LandmarkDto.Res>> getSquareMapLandmarkList(@PathVariable Double lat, @PathVariable Double lot, @PathVariable Double distance) {
        return ResponseEntity.ok(landmarkService.getSquareMapLandmarkList(lat, lot, distance));
    }

}

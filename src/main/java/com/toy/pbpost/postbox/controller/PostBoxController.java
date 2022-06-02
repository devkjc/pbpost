package com.toy.pbpost.postbox.controller;

import com.toy.pbpost.config.security.SecurityService;
import com.toy.pbpost.postbox.dto.PostBoxDto;
import com.toy.pbpost.postbox.repository.LandmarkRepository;
import com.toy.pbpost.postbox.service.PostBoxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/postbox")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
@Api(tags = {"PostBox API"})
public class PostBoxController {

    private final PostBoxService postBoxService;
    private final LandmarkRepository landmarkRepository;

    @PostMapping
    @ApiOperation(value = "우체통 설치")
    public ResponseEntity<PostBoxDto.Res> savePostBox(@RequestBody PostBoxDto.Req req) {
        String uid = SecurityService.getUid();
        return ResponseEntity.ok(postBoxService.savePostBox(uid, req));
    }

    @GetMapping
    @ApiOperation(value = "내 우체통 조회")
    public ResponseEntity<PostBoxDto.Res> getPostBox() {
        String uid = SecurityService.getUid();
        return ResponseEntity.ok(postBoxService.getPostBoxDto(uid));
    }

    @DeleteMapping
    @ApiOperation(value = "우체통 제거")
    public ResponseEntity<?> deletePostBox() {
        String uid = SecurityService.getUid();
        postBoxService.deletePostBox(uid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/map/{lat}/{lon}/{distance}")
    @ApiOperation(value = "내 주변 우체통 조회")
    public ResponseEntity<List<PostBoxDto.Res>> getSquareMapPostBoxList(
            @ApiParam(value = "위도") @PathVariable Double lat,
            @ApiParam(value = "경도") @PathVariable Double lon,
            @ApiParam(value = "거리(km)") @PathVariable Double distance) {
        return ResponseEntity.ok(postBoxService.getSquareMapPostBoxList(lat, lon, distance));
    }

//    @GetMapping("/test")
//    @ApiOperation(value = "랜드마크 좌표 생성")
//    public ResponseEntity<?> gettest() {
//        for (Landmark landmark : landmarkRepository.findAll()) {
//
//            BigDecimal latitude = landmark.getAddress().getLatitude(); // 위도
//            BigDecimal longitude = landmark.getAddress().getLonitude(); // 경도
//            String pointWKT = String.format("POINT(%s %s)", longitude, latitude); // 경도 위도 순
//            try {
//                Point point = (Point) new WKTReader().read(pointWKT);
//                landmark.getAddress().setLocationPoint(point);
//                landmarkRepository.save(landmark);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//        return ResponseEntity.ok().build();
//    }

}

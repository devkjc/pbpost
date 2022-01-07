package com.toy.pbpostbox.postbox.controller;

import com.toy.pbpostbox.config.security.SecurityService;
import com.toy.pbpostbox.postbox.dto.PostBoxDto;
import com.toy.pbpostbox.postbox.repository.LandmarkRepository;
import com.toy.pbpostbox.postbox.service.PostBoxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    public ResponseEntity<List<PostBoxDto.Res>> getPostBox() {
        String uid = SecurityService.getUid();
        return ResponseEntity.ok(postBoxService.getPostBox(uid));
    }

    @DeleteMapping
    @ApiOperation(value = "우체통 제거")
    public ResponseEntity<?> deletePostBox() {
        String uid = SecurityService.getUid();
        postBoxService.deletePostBox(uid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/map/{lat}/{lot}/{distance}")
    @ApiOperation(value = "내 주변 우체통 조회")
    public ResponseEntity<List<PostBoxDto.Res>> getSquareMapPostBoxList(@PathVariable Double lat,@PathVariable Double lot, @PathVariable Double distance) {
        return ResponseEntity.ok(postBoxService.getSquareMapPostBoxList(lat, lot, distance));
    }

//    @GetMapping("/test")
//    @ApiOperation(value = "랜드마크 좌표 생성")
//    public ResponseEntity<?> gettest() {
//
//        for (Landmark landmark : landmarkRepository.findAll()) {
//            BigDecimal latitude = landmark.getAddress().getLatitude();
//            BigDecimal longitude = landmark.getAddress().getLongitude();
//            String pointWKT = String.format("POINT(%s %s)", latitude, longitude);
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

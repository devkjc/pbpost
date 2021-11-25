package com.toy.pbpostbox.postbox.controller;

import com.toy.pbpostbox.config.security.SecurityService;
import com.toy.pbpostbox.postbox.dto.PostBoxDto;
import com.toy.pbpostbox.postbox.service.PostBoxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
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

    @PostMapping
    @ApiOperation(value = "우체통 설치")
    public ResponseEntity<PostBoxDto.Res> savePostBox(@RequestBody PostBoxDto.Req req) {
        String uid = SecurityService.getUid();
        return ResponseEntity.ok(postBoxService.savePostBox(uid, req));
    }

    @GetMapping
    @ApiOperation(value = "우체통 조회")
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

    @GetMapping("/test")
    @ApiOperation(value = "test")
    public ResponseEntity<?> get() {
        throw new InvalidTokenException("test");
    }


}

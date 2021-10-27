package com.toy.pbpostbox.postbox.controller;

import com.toy.pbpostbox.config.security.SecurityService;
import com.toy.pbpostbox.postbox.dto.PostBoxDto;
import com.toy.pbpostbox.postbox.service.PostBoxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public ResponseEntity<?> savePostBox(@RequestBody PostBoxDto.Req req) {
        String uid = SecurityService.getUid();
        return ResponseEntity.ok(postBoxService.savePostBox(uid, req));
    }


}

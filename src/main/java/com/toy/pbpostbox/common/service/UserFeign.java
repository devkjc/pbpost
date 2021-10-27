package com.toy.pbpostbox.common.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("pbuser")
public interface UserFeign {

    @GetMapping(value = "/api/v1/user/id")
    String getUid();

}

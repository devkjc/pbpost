package com.toy.pbpost.bird.service;

import com.toy.pbpost.bird.domain.Bird;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient("pbbird")
public interface BirdFeign {

    @GetMapping("/api/v1/bird/id/{birdId}")
    Optional<Bird> getPostBox(@PathVariable long birdId);

}

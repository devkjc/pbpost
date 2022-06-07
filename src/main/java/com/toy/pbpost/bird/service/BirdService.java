package com.toy.pbpost.bird.service;

import com.toy.pbpost.bird.domain.Bird;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BirdService {

    private final BirdFeign birdFeign;

    public Bird getBird(long birdId) {
        return birdFeign.getPostBox(birdId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 새 정보입니다."));
    }

}

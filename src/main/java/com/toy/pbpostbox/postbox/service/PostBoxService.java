package com.toy.pbpostbox.postbox.service;

import com.toy.pbpostbox.common.service.AuthService;
import com.toy.pbpostbox.postbox.dto.PostBoxDto;
import com.toy.pbpostbox.postbox.repository.PostBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostBoxService {

    private final PostBoxRepository postBoxRepository;
//    private final AuthService authService;

    public void savePostBox(PostBoxDto.Req req) {
        AuthService.getUser();
//        return PostBoxDto.Res.of(postBoxRepository.save(req.toEntity()));
    }
}

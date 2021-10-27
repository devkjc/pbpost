package com.toy.pbpostbox.postbox.service;

import com.toy.pbpostbox.common.service.UserFeign;
import com.toy.pbpostbox.postbox.dto.PostBoxDto;
import com.toy.pbpostbox.postbox.repository.PostBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostBoxService {

    private final PostBoxRepository postBoxRepository;
    private final UserFeign userFeign;

    public PostBoxDto.Res savePostBox(PostBoxDto.Req req) {
        String uid = userFeign.getUid();
        return PostBoxDto.Res.of(postBoxRepository.save(req.toEntity(uid)));
    }
}

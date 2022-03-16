package com.toy.pbpostbox.postbox.service;

import com.toy.pbpostbox.postbox.domain.PostBox;
import com.toy.pbpostbox.postbox.dto.PostBoxDto;
import com.toy.pbpostbox.postbox.repository.PostBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.toy.pbpostbox.postbox.service.GeometryUtil.getLineString;

@Service
@RequiredArgsConstructor
public class PostBoxService {

    private final PostBoxRepository postBoxRepository;

    public PostBoxDto.Res savePostBox(String uid, PostBoxDto.Req req) {
        return PostBoxDto.Res.of(postBoxRepository.save(req.toEntity(uid)));
    }

    @Transactional
    public void deletePostBox(String uid) {
        postBoxRepository.deleteByUid(uid);
    }

    public List<PostBoxDto.Res> getPostBox(String uid) {
        return postBoxRepository.findByUid(uid).stream().map(PostBoxDto.Res::of).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostBoxDto.Res> getSquareMapPostBoxList(double baseLatitude, double baseLongitude, double distance) {

        String lineString = getLineString(baseLatitude, baseLongitude, distance);

        List<PostBox> squareMapPostBoxList = postBoxRepository.getSquareMapPostBoxList(lineString);

        return squareMapPostBoxList.stream().map(PostBoxDto.Res::of).collect(Collectors.toList());
    }

}

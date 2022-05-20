package com.toy.pbpostbox.postbox.service;

import com.toy.pbpostbox.common.exception.ProcessException;
import com.toy.pbpostbox.postbox.domain.PostBox;
import com.toy.pbpostbox.postbox.dto.PostBoxDto;
import com.toy.pbpostbox.postbox.repository.PostBoxRepository;
import com.toy.pbpostbox.user.domain.User;
import com.toy.pbpostbox.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.toy.pbpostbox.common.util.GeometryUtil.getLineString;

@Service
@RequiredArgsConstructor
public class PostBoxService {

    private final PostBoxRepository postBoxRepository;
    private final UserService userService;

    @Transactional
    public PostBoxDto.Res savePostBox(String uid, PostBoxDto.Req req) {
        User user = userService.getUser(uid);
        Optional<PostBox> postBoxOptional = getPostBox(user.getUid());
        if (postBoxOptional.isPresent()) {
            PostBox postBox = postBoxOptional.get();
            postBox.setAddress(req.getAddress());
            return PostBoxDto.Res.of(postBoxRepository.save(postBox));
        }else{
            return PostBoxDto.Res.of(postBoxRepository.save(req.toEntity(user)));
        }
    }

    @Transactional
    public void deletePostBox(String uid) {
        postBoxRepository.deleteByUserUid(uid);
    }

    public PostBoxDto.Res getPostBoxDto(String uid) {
        return getPostBox(uid).map(PostBoxDto.Res::of).orElseThrow(() -> new ProcessException("내 우체통이 없습니다."));
    }

    public Optional<PostBox> getPostBox(String uid) {
        return postBoxRepository.findTopByUserUid(uid);
    }

    @Transactional(readOnly = true)
    public List<PostBoxDto.Res> getSquareMapPostBoxList(double baseLatitude, double baseLongitude, double distance) {

        String lineString = getLineString(baseLatitude, baseLongitude, distance);

        List<PostBox> squareMapPostBoxList = postBoxRepository.getSquareMapPostBoxList(lineString);

        return squareMapPostBoxList.stream().map(PostBoxDto.Res::of).collect(Collectors.toList());
    }

}

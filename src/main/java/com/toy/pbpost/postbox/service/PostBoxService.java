package com.toy.pbpost.postbox.service;

import com.toy.pbpost.common.domain.Address;
import com.toy.pbpost.postbox.domain.PostBox;
import com.toy.pbpost.postbox.dto.PostBoxDto;
import com.toy.pbpost.postbox.repository.PostBoxRepository;
import com.toy.pbpost.user.domain.User;
import com.toy.pbpost.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.toy.pbpost.common.util.GeometryUtil.getLineString;

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
            postBox.setAddress(Address.createAddress(req.getLatitude(), req.getLongitude(), req.getAddress()));
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
        return getPostBox(uid).map(PostBoxDto.Res::of).orElse(null);
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

    public PostBox findById(long id) {
        return postBoxRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 우체통입니다."));
    }

}

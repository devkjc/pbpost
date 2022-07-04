package com.toy.pbpost.postbox.service;

import com.toy.pbpost.postbox.domain.Landmark;
import com.toy.pbpost.postbox.dto.LandmarkDto;
import com.toy.pbpost.postbox.repository.LandmarkRepository;
import com.toy.pbpost.user.domain.User;
import com.toy.pbpost.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.toy.pbpost.common.util.GeometryUtil.getLineString;

@Service
@RequiredArgsConstructor
public class LandmarkService {

    private final LandmarkRepository landmarkRepository;
    private final UserService userService;

    @Transactional(readOnly = true)
    public List<LandmarkDto.SimpleRes> getSquareMapLandmarkList(double baseLatitude, double baseLongitude, double distance) {

        String lineString = getLineString(baseLatitude, baseLongitude, distance);

        List<Landmark> landmarkList = landmarkRepository.getSquareMapLandmarkList(lineString);

        return landmarkList.stream().map(LandmarkDto.SimpleRes::of).collect(Collectors.toList());
    }

    public List<LandmarkDto.SimpleRes> getLandmarkList() {
        return landmarkRepository.findAll().stream().map(LandmarkDto.SimpleRes::of).collect(Collectors.toList());
    }

    public LandmarkDto.Res getLandmark(long landmarkId, String uid) {
        User user = userService.getUser(uid);
        return landmarkRepository.findById(landmarkId).map(landmark -> LandmarkDto.Res.of(landmark, user.getTimezone())).orElse(null);
    }

    public Landmark findById(long id) {
        return landmarkRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 랜드마크 입니다."));
    }
}

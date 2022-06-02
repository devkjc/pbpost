package com.toy.pbpost.postbox.service;

import com.toy.pbpost.postbox.domain.Landmark;
import com.toy.pbpost.postbox.dto.LandmarkDto;
import com.toy.pbpost.postbox.repository.LandmarkRepository;
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

    @Transactional(readOnly = true)
    public List<LandmarkDto.Res> getSquareMapLandmarkList(double baseLatitude, double baseLongitude, double distance) {

        String lineString = getLineString(baseLatitude, baseLongitude, distance);

        List<Landmark> landmarkList = landmarkRepository.getSquareMapLandmarkList(lineString);

        return landmarkList.stream().map(LandmarkDto.Res::of).collect(Collectors.toList());
    }

}

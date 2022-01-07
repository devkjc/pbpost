package com.toy.pbpostbox.postbox.service;

import com.toy.pbpostbox.common.domain.Landmark;
import com.toy.pbpostbox.common.domain.Location;
import com.toy.pbpostbox.postbox.domain.PostBox;
import com.toy.pbpostbox.postbox.dto.LandmarkDto;
import com.toy.pbpostbox.postbox.dto.PostBoxDto;
import com.toy.pbpostbox.postbox.repository.LandmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LandmarkService {

    private final LandmarkRepository landmarkRepository;

    @Transactional(readOnly = true)
    public List<LandmarkDto.Res> getSquareMapLandmarkList(double baseLatitude, double baseLongitude, double distance) {

        // 북동쪽 좌표 구하기
        Location northEast = GeometryUtil.calculate(baseLatitude, baseLongitude, distance, Direction.NORTH_EAST);
        // 남서쪽 좌표 구하기
        Location southWest = GeometryUtil.calculate(baseLatitude, baseLongitude, distance, Direction.SOUTH_WEST);

        double x1 = northEast.getLatitude();
        double y1 = northEast.getLongitude();
        double x2 = southWest.getLatitude();
        double y2 = southWest.getLongitude();

        String lineString = String.format("LINESTRING(%f %f, %f %f)", x1, y1, x2, y2);

        List<Landmark> landmarkList = landmarkRepository.getSquareMapLandmarkList(lineString);

        return landmarkList.stream().map(LandmarkDto.Res::of).collect(Collectors.toList());
    }

}

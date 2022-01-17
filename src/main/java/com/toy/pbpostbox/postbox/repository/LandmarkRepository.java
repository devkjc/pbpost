package com.toy.pbpostbox.postbox.repository;

import com.toy.pbpostbox.postbox.domain.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LandmarkRepository extends JpaRepository<Landmark, Long> {

    @Query(value = "select * from postbird.landmark where MBRCONTAINS(ST_LINESTRINGFROMTEXT(:lineString), location_point)", nativeQuery = true)
    List<Landmark> getSquareMapLandmarkList(@Param(value = "lineString") String lineString);

}

package com.toy.pbpost.postbox.repository;

import com.toy.pbpost.postbox.domain.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
*
* select id,name,ST_DISTANCE_SPHERE(POINT(@lon, @lat), location_point) AS dist from landmark order by dist;
 * */

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark, Long> {

    @Query(value = "select * from postbird.landmark where MBRCONTAINS(ST_LINESTRINGFROMTEXT(:lineString), location_point)", nativeQuery = true)
    List<Landmark> getSquareMapLandmarkList(@Param(value = "lineString") String lineString);

}

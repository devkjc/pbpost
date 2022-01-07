package com.toy.pbpostbox.postbox.repository;

import com.toy.pbpostbox.postbox.domain.PostBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostBoxRepository extends JpaRepository<PostBox, Long> {

    void deleteByUid(String uid);

    List<PostBox> findByUid(String uid);

    @Query(value = "select * from postbird.post_box where MBRCONTAINS(ST_LINESTRINGFROMTEXT(:lineString), location_point)", nativeQuery = true)
    List<PostBox> getSquareMapPostBoxList(@Param(value = "lineString") String lineString);

}

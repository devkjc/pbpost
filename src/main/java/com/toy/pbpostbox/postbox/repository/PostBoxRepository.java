package com.toy.pbpostbox.postbox.repository;

import com.toy.pbpostbox.postbox.domain.PostBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostBoxRepository extends JpaRepository<PostBox, Long> {

    void deleteByUserUid(String uid);

    Optional<PostBox> findTopByUserUid(String uid);

    @Query(value = "select * from postbird.post_box where MBRCONTAINS(ST_LINESTRINGFROMTEXT(:lineString), location_point)", nativeQuery = true)
    List<PostBox> getSquareMapPostBoxList(@Param(value = "lineString") String lineString);

    @Query(value = "select pb.*\n" +
            "from postbird.address_book ab inner join postbird.post_box pb on ab.post_box_id_fk = pb.id\n" +
            "where ab.uid_fk = :uid and MBRCONTAINS(ST_LINESTRINGFROMTEXT(:lineString), location_point)", nativeQuery = true)
    List<PostBox> getSquareMapMyAddressBook(@Param(value = "lineString") String lineString, @Param(value = "uid") String uid);

}

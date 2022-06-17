package com.toy.pbpost.letter.repository;

import com.toy.pbpost.letter.domain.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface LetterRepository extends JpaRepository<Letter, Long> {

    @Query(value = "select count(*) from postbird.letter l where l.bird_id_fk = :birdId and l.arrival_time >= :now ", nativeQuery = true)
    Long countByBirdWorking(@Param(value = "birdId") long birdId, @Param(value = "now") LocalDateTime now);

    @Query(value = "select * from postbird.letter l where l.from_uid_fk = :uid and l.arrival_time >= :now ", nativeQuery = true)
    List<Letter> sendLetterList(@Param(value = "uid") String uid, @Param(value = "now") LocalDateTime now);

    void deleteByIdAndFromUid(long letterId, String from_uid);

}

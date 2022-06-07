package com.toy.pbpost.letter.repository;

import com.toy.pbpost.letter.domain.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface LetterRepository extends JpaRepository<Letter, Long> {

    @Query(value = "select count(*) from postbird.letter l where l.bird_id_fk = :birdId and l.arrival_time >= :now ", nativeQuery = true)
    Long countByBirdWorking(@Param(value = "birdId") long birdId, @Param(value = "now") LocalDateTime now);

}

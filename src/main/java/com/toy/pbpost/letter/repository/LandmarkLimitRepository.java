package com.toy.pbpost.letter.repository;

import com.toy.pbpost.postbox.domain.LandmarkLimit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface LandmarkLimitRepository extends JpaRepository<LandmarkLimit, Long> {

    Optional<LandmarkLimit> findByUidAndDate(String uid, LocalDate date);

}

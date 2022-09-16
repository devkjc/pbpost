package com.toy.pbpost.letter.repository;

import com.toy.pbpost.letter.domain.LetterBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LetterBoxRepository extends JpaRepository<LetterBox, Long> {

    Optional<LetterBox> findByUserUid(String uid);

}

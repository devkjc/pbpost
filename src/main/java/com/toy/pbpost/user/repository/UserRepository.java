package com.toy.pbpost.user.repository;

import com.toy.pbpost.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findTopByCodeAndUidNot(String code, String uid);
    Optional<User> findTopByNickNameAndUidNot(String nickname, String uid);

}

package com.toy.pbpostbox.user.repository;

import com.toy.pbpostbox.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findTopByCode(String code);

}

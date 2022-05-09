package com.toy.pbpostbox.user.service;

import com.toy.pbpostbox.user.domain.User;
import com.toy.pbpostbox.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser(String uid) {
        return userRepository.findById(uid).orElseThrow(IllegalArgumentException::new);
    }
}

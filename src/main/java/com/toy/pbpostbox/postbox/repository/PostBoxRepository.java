package com.toy.pbpostbox.postbox.repository;

import com.toy.pbpostbox.postbox.domain.PostBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostBoxRepository extends JpaRepository<PostBox, Long> {
}

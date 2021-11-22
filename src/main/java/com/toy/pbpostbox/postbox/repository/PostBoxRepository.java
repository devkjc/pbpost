package com.toy.pbpostbox.postbox.repository;

import com.toy.pbpostbox.postbox.domain.PostBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostBoxRepository extends JpaRepository<PostBox, Long> {

    void deleteByUid(String uid);

    List<PostBox> findByUid(String uid);

}

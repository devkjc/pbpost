package com.toy.pbpostbox.postbox.domain;

import com.toy.pbpostbox.common.domain.Address;
import com.toy.pbpostbox.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "post_box")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostBox extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(name = "uid_fk")
    private long uid;

    @Embedded
    private Address address;

}

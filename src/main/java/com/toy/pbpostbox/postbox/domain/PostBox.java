package com.toy.pbpostbox.postbox.domain;

import com.toy.pbpostbox.common.domain.Address;
import com.toy.pbpostbox.common.domain.BaseTimeEntity;
import com.toy.pbpostbox.user.domain.User;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uid_fk")
    private User user;

    @Embedded
    private Address address;

    public void setAddress(Address address) {
        this.address = address;
    }
}

package com.toy.pbpost.postbox.domain;

import com.toy.pbpost.common.domain.Address;
import com.toy.pbpost.common.domain.BaseTimeEntity;
import com.toy.pbpost.letter.domain.Letter;
import com.toy.pbpost.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "toPostBox")
    @Where(clause = "arrival_time <= UTC_TIMESTAMP()")
    private List<Letter> letterList = new ArrayList<>();

    public void setAddress(Address address) {
        this.address = address;
    }
}

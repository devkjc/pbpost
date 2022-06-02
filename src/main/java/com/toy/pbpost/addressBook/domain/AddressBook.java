package com.toy.pbpost.addressBook.domain;

import com.toy.pbpost.common.domain.BaseTimeEntity;
import com.toy.pbpost.postbox.domain.PostBox;
import com.toy.pbpost.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "address_book")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressBook extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid_fk")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_box_id_fk", nullable = false)
    private PostBox postBox;

}

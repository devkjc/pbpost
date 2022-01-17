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
@Table(name = "landmark")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Landmark extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String name;
    private String engName;

    @Embedded
    private Address address;

}

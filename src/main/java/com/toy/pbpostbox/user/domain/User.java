package com.toy.pbpostbox.user.domain;

import com.toy.pbpostbox.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    private String uid;

    @Column
    private String nickName;

    @Column(unique = true, nullable = false, updatable = false)
    private String code;

}

package com.toy.pbpost.user.domain;

import com.toy.pbpost.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

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

    private String timezone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid) && Objects.equals(code, user.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, code);
    }
}

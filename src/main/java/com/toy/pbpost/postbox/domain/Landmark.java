package com.toy.pbpost.postbox.domain;

import com.toy.pbpost.common.domain.Address;
import com.toy.pbpost.common.domain.BaseTimeEntity;
import com.toy.pbpost.letter.domain.Letter;
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

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "toLandmark")
    @Where(clause = "arrival_time <= UTC_TIMESTAMP()")
    private List<Letter> letterList = new ArrayList<>();

}

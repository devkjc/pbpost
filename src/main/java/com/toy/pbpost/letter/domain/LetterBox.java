package com.toy.pbpost.letter.domain;

import com.toy.pbpost.common.domain.BaseTimeEntity;
import com.toy.pbpost.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "letter_box")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LetterBox extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid_fk")
    private User user;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "letterBox")
    private List<Letter> letterList = new ArrayList<>();

    public void receiptLetter(Letter letter) {
        letterList.add(letter);
    }

}

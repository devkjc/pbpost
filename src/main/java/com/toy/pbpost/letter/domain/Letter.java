package com.toy.pbpost.letter.domain;

import com.toy.pbpost.common.domain.BaseTimeEntity;
import com.toy.pbpost.postbox.domain.Landmark;
import com.toy.pbpost.postbox.domain.PostBox;
import com.toy.pbpost.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "letter")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Letter extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid_fk")
    private User from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landmark_id_fk")
    private Landmark toLandmark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_box_id_fk")
    private PostBox toPostBox;

    @ManyToOne
    @JoinColumn(name = "font_id_fk")
    private LetterFont font;

    @ManyToOne
    @JoinColumn(name = "background_id_fk")
    private LetterBackground background;

    @Column(columnDefinition = "TEXT")
    private String content;


}
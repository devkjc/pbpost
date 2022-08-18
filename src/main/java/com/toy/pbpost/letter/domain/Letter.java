package com.toy.pbpost.letter.domain;

import com.toy.pbpost.bird.domain.Bird;
import com.toy.pbpost.common.domain.Address;
import com.toy.pbpost.common.domain.BaseTimeEntity;
import com.toy.pbpost.postbox.domain.Landmark;
import com.toy.pbpost.postbox.domain.PostBox;
import com.toy.pbpost.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JoinColumn(name = "from_uid_fk")
    private User from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_uid_fk")
    private User to;

    @ManyToOne
    @JoinColumn(name = "bird_id_fk")
    private Bird bird;

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

    @Embedded
    private Address departureAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "return_post_box_id_fk")
    private PostBox returnPostBox;

    @Column(nullable = false, columnDefinition = "편지 도착 후 새가 돌아오는 시간")
    private LocalDateTime returnTime;

    @Column(nullable = false, columnDefinition = "편지 발송 시간")
    private LocalDateTime departureTime;

    @Column(nullable = false, columnDefinition = "편지 도착 시간")
    private LocalDateTime arrivalTime;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder.Default
    private Boolean enabled = true;

}

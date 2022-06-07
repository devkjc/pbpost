package com.toy.pbpost.bird.domain;

import com.toy.pbpost.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "bird")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bird extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(name = "uid_fk")
    private String uid;

    private String birdName;

    @Builder.Default
    private Double hourly = 60d;

    public void setBirdName(String birdName) {
        this.birdName = birdName;
    }
}

package com.toy.pbpost.postbox.domain;

import com.toy.pbpost.common.domain.BaseTimeEntity;
import com.toy.pbpost.common.exception.ProcessException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "landmark_limit")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LandmarkLimit extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uid;

    private LocalDate date;

    private String timezone;

    private Long count;

    public void addCount() {
        if (count >= 3) {
            throw new ProcessException("최대 수령 횟수를 초과했습니다.");
        }else {
            count++;
        }
    }
}

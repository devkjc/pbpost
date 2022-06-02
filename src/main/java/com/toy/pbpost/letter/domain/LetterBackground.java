package com.toy.pbpost.letter.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "letter_background")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LetterBackground {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String name;

}

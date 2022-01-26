package com.ssafy.BackEnd.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Table(name = "teamkeyword")
@Getter @Setter
public class TeamKeyword {
    @Id
    @GeneratedValue
    @Column(name="teamkeyword_id")
    long teamkeyword_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    Team team;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "keyword_id")
    Keyword keyword;
}

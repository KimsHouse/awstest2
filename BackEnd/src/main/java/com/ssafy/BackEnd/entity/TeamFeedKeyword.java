package com.ssafy.BackEnd.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Table(name = "teamfeedkeyword")
@Getter @Setter
public class TeamFeedKeyword {

    @Id @GeneratedValue
    long teamfeedkeyword_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "keyword_id")
    Keyword keyword;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "teamfeed_id")
    TeamFeed team_feed;

}

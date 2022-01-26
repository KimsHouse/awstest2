package com.ssafy.BackEnd.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Table(name = "teamfeedfile")
@Getter @Setter
public class TeamFeedFile {

    @Id @GeneratedValue
    long teamfeedfile_id;

    String file_name;

    String savefolder;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "teamfeed_id")
    TeamFeed team_feed;
}

package com.ssafy.BackEnd.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teamfeed")
@RequiredArgsConstructor
@Getter @Setter

public class TeamFeed {
    @Id @GeneratedValue
    long teamfeed_id;

    String content;

    @CreatedDate
    LocalDateTime create_time;

    @LastModifiedDate
    LocalDateTime modify_time;

    @ManyToOne
    @JoinColumn(name = "team_id")
    Team team;

    @OneToMany(mappedBy = "team_feed", cascade = CascadeType.ALL) // many로 끝날때 fetch nono
    List<TeamFeedFile> teamfeed_file = new ArrayList<>();

    @OneToMany(mappedBy = "team_feed", cascade = CascadeType.ALL)
    List<TeamFeedKeyword> teamfeed_keyword = new ArrayList<>();
}

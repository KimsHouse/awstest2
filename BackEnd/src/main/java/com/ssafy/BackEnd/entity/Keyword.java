package com.ssafy.BackEnd.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "keyword")
public class Keyword {
    @Id
    long keyword_id;

    String name;

    @OneToMany(mappedBy = "keyword")
    List<TeamKeyword> team_keyword = new ArrayList<>();

    @OneToMany(mappedBy = "keyword")
    List<TeamFeedKeyword> team_feed_keyword = new ArrayList<>();

    @OneToMany(mappedBy = "keyword")
    List<UserFeedKeyword> user_feed_keyword = new ArrayList<>();

//    @OneToMany(mappedBy = "keyword")
//    List<ProfileKeyword> profile_keyword = new ArrayList<>();


}

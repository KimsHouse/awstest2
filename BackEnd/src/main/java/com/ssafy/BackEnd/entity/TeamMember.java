package com.ssafy.BackEnd.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "teammember")
@RequiredArgsConstructor
@Getter @Setter
public class TeamMember {
    @Id
    @GeneratedValue
    long teammember_id; // 혹시? notblank ??

    @ManyToOne
    @JoinColumn(name = "team_id")
    Team team;

    @ManyToOne
    @JoinColumn(name = "email")
    User user;

}

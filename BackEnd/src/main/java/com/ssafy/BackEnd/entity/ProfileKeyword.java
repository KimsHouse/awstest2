//package com.ssafy.BackEnd.entity;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "profilekeyword")
//@RequiredArgsConstructor
//@Getter @Setter
//public class ProfileKeyword {
//
//    @Id @GeneratedValue
//    private Long profilekeyword_id;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "profile_id")
//    private Profile profile;
//
//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "keyword_id")
//    private Keyword keyword;
//}

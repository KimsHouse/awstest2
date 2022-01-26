//package com.ssafy.BackEnd.entity;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name = "image")
//@Getter @Setter
//
//public class Image {
//
//    @Id @GeneratedValue
//    Long image_id;
//
//    @Column(nullable = false)
//    String orig_image_name;
//
//    @Column(nullable = false)
//    String image_name;
//
//    @Column(nullable = false)
//    String savefolder;
//
//    @Builder
//    public Image(Long image_id, String orig_image_name, String image_name, String savefolder) {
//        this.image_id = image_id;
//        this.orig_image_name = orig_image_name;
//        this.image_name = image_name;
//        this.savefolder = savefolder;
//    }
//
//    public Image(String savefolder) {
//        this.savefolder = savefolder;
//    }
//}

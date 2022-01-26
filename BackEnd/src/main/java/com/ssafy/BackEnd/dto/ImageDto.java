//package com.ssafy.BackEnd.dto;
//
//import com.ssafy.BackEnd.entity.Image;
//import lombok.*;
//
//@Getter @Setter
//@ToString
//@NoArgsConstructor
//public class ImageDto {
//
//    private Long image_id;
//    private String orig_image_name;
//    private String image_name;
//    private String savefolder;
//
//    public Image toEntity() {
//        Image build = Image.builder()
//                .image_id(image_id)
//                .orig_image_name(orig_image_name)
//                .image_name(image_name)
//                .savefolder(savefolder)
//                .build();
//        return build;
//    }
//
//    @Builder
//    public ImageDto(Long image_id, String orig_image_name, String image_name, String savefolder) {
//        this.image_id = image_id;
//        this.orig_image_name = orig_image_name;
//        this.image_name = image_name;
//        this.savefolder = savefolder;
//    }
//}

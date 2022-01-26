package com.ssafy.BackEnd.dto;

import com.ssafy.BackEnd.entity.FileType;
import com.ssafy.BackEnd.entity.Profile;
import com.ssafy.BackEnd.entity.User;
import com.ssafy.BackEnd.entity.UserFeed;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor
@Data
public class UserDto {

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @Builder
    public UserDto(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public User createUser() {
        return User.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();

    }
}

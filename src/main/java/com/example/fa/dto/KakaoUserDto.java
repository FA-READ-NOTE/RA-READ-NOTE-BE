package com.example.fa.dto;

import com.example.fa.entity.Role;
import com.example.fa.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class KakaoUserDto {
    private String email;
    private String nickname;
    private String password;


    public KakaoUserDto(String email, String nickname, String password){
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    @Builder
    public User toEntity() {
        return User.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .role(Role.USER)
                .build();
    }
}

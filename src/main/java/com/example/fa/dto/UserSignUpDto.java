package com.example.fa.dto;

import com.example.fa.entity.Role;
import com.example.fa.entity.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpDto {
    @NotBlank(message = "아이디를 입력해주세요")
    @Email(message = "이메일 형식이 아닙니다") // 이메일 형식
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요")
    @Size(min = 2, message = "닉네임이 너무 짧습니다")
    private String nickname;
    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
            message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String password;
    private String checkedPassword;
    private Role role;

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

package com.example.fa.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true)
    private String email; // 아이디 역할
    private String password;
    @Column(unique = true)
    private  String nickname;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(columnDefinition="LONGTEXT")
    private String image;

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }
    // 비밀번호 변경, 회원탈퇴시 비밀번호를 확인하며, 이때 비밀번호의 일치여부를 판단하는 메서드
    public boolean matchPassword(PasswordEncoder passwordEncoder, String checkPassword) {
        return passwordEncoder.matches(checkPassword, getPassword());
    }

    // 회원가입 시, = USER의 권한을 부여하는 메서드
    public void addUserAuthority() {
        this.role = Role.USER;
    }
}

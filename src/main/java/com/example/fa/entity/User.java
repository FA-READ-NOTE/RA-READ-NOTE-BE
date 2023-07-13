package com.example.fa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
}

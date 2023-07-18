package com.example.fa.service;

import com.example.fa.config.jwt.JwtTokenProvider;
import com.example.fa.dto.UserLoginDto;
import com.example.fa.dto.UserSignUpDto;
import com.example.fa.entity.User;
import com.example.fa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public User signup(UserSignUpDto dto) throws Exception {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new Exception("이미 존재하는 이메일입니다.");
        }
        if (!dto.getPassword().equals(dto.getCheckedPassword())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        User user = dto.toEntity();
        user.addUserAuthority();
        user.encodePassword(passwordEncoder);

        userRepository.save(user);
        return user;
    }

    public String login(UserLoginDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(()->new IllegalArgumentException("가입되지 않은 이메일입니다."));

        String password = dto.getPassword();
        if(!user.matchPassword(passwordEncoder, password)) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        List<String> roles = new ArrayList<>();
        roles.add(user.getRole().name());

        return jwtTokenProvider.createToken(user.getEmail(), roles);
    }
}

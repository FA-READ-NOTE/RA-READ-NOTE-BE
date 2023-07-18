package com.example.fa.member.repository;

import com.example.fa.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberRepository extends JpaRepository<MemberEntity, Long> {
}

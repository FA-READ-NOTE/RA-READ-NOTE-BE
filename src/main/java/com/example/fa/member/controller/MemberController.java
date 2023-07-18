package com.example.fa.member.controller;

import com.example.fa.member.dto.MemberDTO;
import com.example.fa.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성사 주입
    private final MemberService memberService;
    // 회원 가입 요청
    @GetMapping("member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
//    public String save(@RequestParam("memberEmail") String memberEmail,
//                       @RequestParam("memberPassword") String memberPassWord,
//                       @RequestParam("memberName") String memberName ) {
//        System.out.println("MemberController.save");
//        System.out.println("memberEmail =" + memberEmail + "memberPassword =" + memberPassWord + "memberName =" + memberName);
//        return "index";
//    }

    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController");
        System.out.println("MemberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "index";
    }
}

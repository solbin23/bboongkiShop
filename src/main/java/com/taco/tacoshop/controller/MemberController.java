package com.taco.tacoshop.controller;

import com.taco.tacoshop.Member.Member;
import com.taco.tacoshop.dto.MemberDto;
import com.taco.tacoshop.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {

   private final MemberService memberService;
   private final PasswordEncoder passwordEncoder;


   @GetMapping("/new")
    public String memberForm(Model model){
       log.info("등록페이지");
       model.addAttribute("memberDto",new MemberDto());
       return "member/memberForm";
   }

   @PostMapping("/new")
    public String memberForm(@Valid MemberDto memberDto, BindingResult bindingResult,Model model){
       if (bindingResult.hasErrors()){
           return "member/memberForm";
       }
      try {
          Member member = Member.createMember(memberDto,passwordEncoder);
          memberService.saveMember(member);

      }catch (IllegalStateException e) {
          model.addAttribute("errorMessage",e.getMessage());
          return "member/memberForm";
      }
      return "redirect:/";
   }

   @GetMapping("/login")
    public String loginMember(){
       return "login";
   }

   @GetMapping("/login/error")
    public String loginError(Model model){
       model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요!");
       return "login";
   }
}

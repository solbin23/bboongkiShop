package com.taco.tacoshop;

import com.taco.tacoshop.Member.Member;
import com.taco.tacoshop.dto.MemberDto;
import com.taco.tacoshop.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(){
        MemberDto memberDto = MemberDto.builder()
                .email("aaaa@email.com")
                .name("뿡키샵")
                .address("경기도 고양시")
                .password("12345")
                .build();
        return Member.createMember(memberDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입")
    public void saveMemberTest(){
        Member member = createMember();
        Member saveMember = memberService.saveMember(member);

        Assertions.assertEquals(member.getEmail(),saveMember.getEmail());
    }
}

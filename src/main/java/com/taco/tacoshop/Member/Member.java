package com.taco.tacoshop.Member;

import com.taco.tacoshop.dto.MemberDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "member")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Builder
    public Member(String email, String name, String password, String address, MemberRole role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder){
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .name(memberDto.getName())
                .address(memberDto.getAddress())
                .password(passwordEncoder.encode(memberDto.getPassword())) //암호화
                .role(MemberRole.USER)
                .build();
        return member;

    }
}

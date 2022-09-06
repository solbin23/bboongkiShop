package com.taco.tacoshop;

import com.taco.tacoshop.Member.Member;
import com.taco.tacoshop.domain.Cart;
import com.taco.tacoshop.dto.MemberDto;
import com.taco.tacoshop.repository.CartRepository;
import com.taco.tacoshop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

@SpringBootTest
@Transactional
public class CartTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

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
    @DisplayName("장바구니 테스트")
    public void CartAndMemberTest(){
        Member member = createMember();
        memberRepository.save(member);

        Cart cart = Cart.builder()
                .member(member)
                .build();
        cartRepository.save(cart);

        em.flush();;
        em.clear();

        Cart saveCart = cartRepository.findById(cart.getId())
                .orElseThrow(EntityNotFoundException::new);
        Assertions.assertEquals(saveCart.getMember().getId(), member.getId());
    }
}

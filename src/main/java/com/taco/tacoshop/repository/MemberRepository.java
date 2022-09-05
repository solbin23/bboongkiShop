package com.taco.tacoshop.repository;

import com.taco.tacoshop.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;



public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}

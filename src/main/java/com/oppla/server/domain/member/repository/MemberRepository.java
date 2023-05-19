package com.oppla.server.domain.member.repository;

import com.oppla.server.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Boolean existsByNickname(String nickname);
    Optional<Member> findByEmail(String email);
}

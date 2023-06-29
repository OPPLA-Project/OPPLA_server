package com.oppla.server.domain.member.repository;

import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.member.enums.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Boolean existsByNickname(String nickname);
    Boolean existsByEmail(String email);
    Optional<Member> findMemberBySnsTypeAndSnsMemberIdAndStatus(String snsType, String snsMemberId, MemberStatus status);
    Member findMemberById (Long memberId);
}

package com.oppla.server.domain.member.repository;

import com.oppla.server.domain.member.entity.NowLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NowLocationRepository extends JpaRepository<NowLocation, Long> {
    Optional<NowLocation> findByMemberId(Long memberId);
}

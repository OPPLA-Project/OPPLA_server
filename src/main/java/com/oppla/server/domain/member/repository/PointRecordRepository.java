package com.oppla.server.domain.member.repository;

import com.oppla.server.domain.member.entity.PointRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRecordRepository extends JpaRepository<PointRecord, Long> {
}

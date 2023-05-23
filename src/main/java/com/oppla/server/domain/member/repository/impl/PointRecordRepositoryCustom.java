package com.oppla.server.domain.member.repository.impl;

import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.member.entity.PointRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PointRecordRepositoryCustom {
    Page<PointRecord> getMemberPointRecordPage(Member member, Pageable pageable);
}

package com.oppla.server.domain.member.repository.impl;

import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.member.entity.PointRecord;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.oppla.server.domain.member.entity.QPointRecord.pointRecord;

@RequiredArgsConstructor
public class PointRecordRepositoryImpl implements PointRecordRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<PointRecord> getMemberPointRecordPage(Member member, Pageable pageable) {
        List<PointRecord> content = jpaQueryFactory.selectFrom(pointRecord)
                .where(pointRecord.member.eq(member))
                .orderBy(pointRecord.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // count query
        JPAQuery<PointRecord> countQuery = jpaQueryFactory.selectFrom(pointRecord)
                .where(pointRecord.member.eq(member))
                .orderBy(pointRecord.createdAt.desc());

        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetch().size());
    }
}

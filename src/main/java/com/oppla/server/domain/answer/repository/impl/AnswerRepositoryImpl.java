package com.oppla.server.domain.answer.repository.impl;

import com.oppla.server.domain.answer.entity.Answer;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;

import static com.oppla.server.domain.answer.entity.QAnswer.answer;
import static com.oppla.server.domain.member.entity.QMember.member;

@RequiredArgsConstructor
public class AnswerRepositoryImpl implements AnswerRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Answer> findAllByQuestionId(Long questionId, Pageable pageable) {

        return jpaQueryFactory.selectFrom(answer)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(answerFilterSort(pageable))
                .fetch();
    }

    private OrderSpecifier<?> answerFilterSort(Pageable page){
        if (!page.getSort().isEmpty()) {
            for (Sort.Order order : page.getSort()) {
                Order direction = order.getDirection().isDescending() ? Order.ASC : Order.DESC;

                switch (order.getProperty()){
                    case "createdAt":
                        return new OrderSpecifier<>(direction, answer.createdAt);
                    case "review":
                        return new OrderSpecifier<>(direction, answer.member.reviewScore);
                    case "answerNum": // 이거 모르겠어요 ㅠㅠ
//                        return new OrderSpecifier<>(direction, answer.member.id.count());
                    case "pick":
                        //return new OrderSpecifier<>(direction, answer.recentPoint);
                }
            }
        }
        return new OrderSpecifier<>(Order.DESC, answer.createdAt);
    }

}

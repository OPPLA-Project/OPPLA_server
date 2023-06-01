package com.oppla.server.domain.question.repository.impl;

import com.oppla.server.domain.member.enums.Gender;
import com.oppla.server.domain.question.dto.QQuestionResDto;
import com.oppla.server.domain.question.dto.QuestionResDto;
import com.oppla.server.domain.question.enums.QuestionStatus;
import com.querydsl.core.types.dsl.MathExpressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.oppla.server.domain.question.entity.QQuestion.question;

@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<QuestionResDto> findQuestionByGenderAndLocation(Gender gender, Double latitude, Double longitude) {
        return jpaQueryFactory
                .select(new QQuestionResDto(question))
                .from(question)
                .where(question.status.eq(QuestionStatus.ACTIVE))
                .where(question.gender.eq(gender))
                .where(calculateDistance(latitude, longitude, question.latitude, question.longitude).loe(0.5))
                .orderBy(calculateDistance(latitude, longitude, question.latitude, question.longitude).asc())
                .fetch();
    }

    private NumberExpression<Double> calculateDistance(Double doubleLat1, Double doubleLong1,
                                                       NumberPath<Double> latitude2, NumberPath<Double> longitude2) {
        NumberExpression<Double> latitude1 = latitude2.divide(latitude2).multiply(doubleLat1);
        NumberExpression<Double> theta = longitude2.add(-1 * doubleLong1).multiply(-1);
        NumberExpression<Double> degree = MathExpressions.sin(degreeToRadian(latitude1))
                .multiply(MathExpressions.sin(degreeToRadian(latitude2)))
                .add(MathExpressions.cos(degreeToRadian(latitude1))
                        .multiply(MathExpressions.cos(degreeToRadian(latitude2)))
                        .multiply(MathExpressions.cos(degreeToRadian(theta))));
        degree = MathExpressions.acos(degree);
        degree = radianToDegree(degree);
        degree = degree.multiply(60 * 1.1515 * 1609.344);

        return degree.divide(1000);
    }

    private NumberExpression<Double> degreeToRadian(NumberExpression<Double> degree) {
        return degree.multiply(Math.PI / 180.0);
    }

    private NumberExpression<Double> radianToDegree(NumberExpression<Double> radian) {
        return radian.multiply(180 / Math.PI);
    }
}

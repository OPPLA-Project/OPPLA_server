package com.oppla.server.domain.member.service;

import com.oppla.server.domain.answer.repository.AnswerRepository;
import com.oppla.server.domain.member.dto.MemberNicknameDuplReqDto;
import com.oppla.server.domain.member.dto.MemberNicknameDuplResDto;
import com.oppla.server.domain.member.dto.MemberProfilePatchReqDto;
import com.oppla.server.domain.member.dto.MemberProfileResDto;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.member.exception.MemberNotFoundException;
import com.oppla.server.domain.member.repository.MemberRepository;
import com.oppla.server.domain.review.entity.Review;
import com.oppla.server.domain.review.entity.dto.AnswerSpeedResDto;
import com.oppla.server.domain.review.entity.dto.InfoScoreResDto;
import com.oppla.server.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AnswerRepository answerRepository;
    private final ReviewRepository reviewRepository;

    public MemberProfileResDto forMemberProfile(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);

        // 채택률
        // 답변 수
        Integer answerNum = answerRepository.countByMemberId(member.getId());
        // 채택된 수
        Integer selectionNum = answerRepository.countByMemberIdAndSelection(member.getId(), true);
        Double memberSelectionPercent = (Math.round((double) selectionNum / (double) answerNum * 100.0*100)/100.0);

        // 리뷰 평가
        List<Review> memberReviewList = reviewRepository.findAllByMemberId(member.getId());
        List<Integer> memberReviewCountList = Arrays.asList(0,0,0,0);

        memberReviewList.forEach(review -> {
            int nowNum = memberReviewCountList.get(review.getInfoScore()-1);
            memberReviewCountList.set(review.getInfoScore()-1, nowNum+1);
        });

        InfoScoreResDto infoScoreResDto = InfoScoreResDto.builder()
                .infoScore1Count(memberReviewCountList.get(0))
                .infoScore2Count(memberReviewCountList.get(1))
                .infoScore3Count(memberReviewCountList.get(2))
                .infoScore4Count(memberReviewCountList.get(3))
                .build();

        // 답변 속도

        List<Integer> memberAnswerSpeedList = Arrays.asList(0,0,0);

        memberReviewList.forEach(review -> {
            int nowNum = memberReviewCountList.get(review.getSpeedScore()-1);
            memberReviewCountList.set(review.getSpeedScore()-1, nowNum+1);
        });

        AnswerSpeedResDto answerSpeedResDto = AnswerSpeedResDto.builder()
                .answerFastCount(memberAnswerSpeedList.get(0))
                .answerSosoCount(memberAnswerSpeedList.get(1))
                .answerSlowCount(memberAnswerSpeedList.get(2))
                .build();


        return MemberProfileResDto.builder()
                .imgUrl(member.getProfileUrl())
                .nickname(member.getNickname())
                .point(member.getPoint())
                .reviewScore(member.getReviewScore())
                .selectionPercent(memberSelectionPercent)
                .intro(member.getIntro())
                .mainAnswerArea(null) // 지역을 어떻게 가져오지? Question에서 지역을 따로 관리하지 않음..
                .infoScore(infoScoreResDto)
                .answerSpeed(answerSpeedResDto)
                .build();
    }

    @Transactional
    public void changeMemberProfile(Member member, MemberProfilePatchReqDto dto) {
        member.changeUserProfile(dto.getNickname(), dto.getIntro());
        memberRepository.save(member);
    }

    public MemberNicknameDuplResDto checkMemberNickname(MemberNicknameDuplReqDto dto) {
        Boolean nicknameStatus = memberRepository.existsByNickname(dto.getNickname());

        return MemberNicknameDuplResDto.builder()
                .duplicationStatus(nicknameStatus)
                .build();
    }
}

package com.oppla.server.domain.member.service;

import com.oppla.server.domain.answer.repository.AnswerRepository;
import com.oppla.server.domain.member.dto.*;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.member.entity.PointRecord;
import com.oppla.server.domain.member.exception.MemberNotFoundException;
import com.oppla.server.domain.member.repository.MemberRepository;
import com.oppla.server.domain.member.repository.PointRecordRepository;
import com.oppla.server.domain.review.entity.Review;
import com.oppla.server.domain.review.dto.AnswerSpeedResDto;
import com.oppla.server.domain.review.dto.InfoScoreResDto;
import com.oppla.server.domain.review.repository.ReviewRepository;
import com.oppla.server.global.common.response.PaginationResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AnswerRepository answerRepository;
    private final ReviewRepository reviewRepository;
    private final PointRecordRepository pointRecordRepository;

    @Transactional
    public MemberProfileResDto forMemberProfile(Member currentMember, Long memberId){
        Member targetMember = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        // 채택률
        // 답변 수
        Integer answerNum = answerRepository.countByMemberId(targetMember.getId());
        // 채택된 수
        Integer selectionNum = answerRepository.countByMemberIdAndSelection(targetMember.getId(), true);
        Double memberSelectionPercent = (Math.round((double) selectionNum / (double) answerNum * 100.0*100)/100.0);

        // 리뷰 평가
        List<Review> memberReviewList = reviewRepository.findAllByMemberId(targetMember.getId());
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
                .imgUrl(targetMember.getProfileUrl())
                .nickname(targetMember.getNickname())
                .point(targetMember.getPoint())
                .reviewScore(targetMember.getReviewScore())
                .selectionPercent(memberSelectionPercent)
                .intro(targetMember.getIntro())
                .mainAnswerArea(null) // 지역을 어떻게 가져오지? Question에서 지역을 따로 관리하지 않음..
                .infoScore(infoScoreResDto)
                .answerSpeed(answerSpeedResDto)
                .isMine(Objects.equals(currentMember.getId(), targetMember.getId()))
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

    @Transactional
    public void changeQuestionTF(Member member) {
        member.changeQuestionTF();
        memberRepository.save(member);
    }

    public PaginationResDto<MemberPointRecordResDto> forMemberPointRecord(Member member, Pageable pageable) {
        Page<PointRecord> page = pointRecordRepository.getMemberPointRecordPage(member, pageable);

        // PointRecord -> MemberPointRecordResDto 생성
        List<MemberPointRecordResDto> dtoList =
                page.stream()
                        .map(pointRecord -> MemberPointRecordResDto.builder()
                                                                    .description(pointRecord.getDescription())
                                                                    .deal_point(pointRecord.getDeal_point())
                                                                    .rest_point(pointRecord.getRest_point())
                                                                    .build()
                ).collect(Collectors.toList());

        return PaginationResDto.<MemberPointRecordResDto>builder()
                .page(page.getNumber())
                .hasNext(page.hasNext())
                .content(dtoList)
                .build();
    }
}

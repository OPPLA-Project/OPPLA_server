package com.oppla.server.domain.member.controller;

import com.oppla.server.domain.member.dto.MemberNicknameDuplReqDto;
import com.oppla.server.domain.member.dto.MemberNicknameDuplResDto;
import com.oppla.server.domain.member.dto.MemberProfilePatchReqDto;
import com.oppla.server.domain.member.dto.MemberProfileResDto;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.member.service.MemberService;
import com.oppla.server.global.common.response.BaseDataResponse;
import com.oppla.server.global.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @Operation(
            summary = "유저의 마이페이지 보기",
            description = "특정 유저의 마이페이지 보기"
    )
    @GetMapping("/member/{memberId}")
    public BaseDataResponse<MemberProfileResDto> getMemberProfile(@PathVariable("memberId") Long memberId){

        return BaseDataResponse.<MemberProfileResDto>builder()
                .result(memberService.forMemberProfile(memberId))
                .build();

    }

    @Operation(
            summary = "유저 프로필 수장",
            description = "유저의 닉네임과, 한줄 소개를 수정" +
                    "\n 닉네임과 한줄 소개 모두 데이터가 있어야햠." +
                    "\n 닉네임만 수정하는 경우 한줄 소개에는 old 데이터가 와야함."
    )
    @PatchMapping("/member")
    public BaseResponse patchMemberProfile(@AuthenticationPrincipal Member member, @RequestBody MemberProfilePatchReqDto dto){
        memberService.changeMemberProfile(member, dto);
        return new BaseResponse();

    }

    @Operation(
            summary = "유저 닉네임 중복 검사",
            description = "유저의 닉네임 수정 시 완료버튼을 누르기 전 닉네임 중복검사"
    )
    @GetMapping("/member/nickname/duplication")
    public BaseDataResponse<MemberNicknameDuplResDto> checkMemberNicknameDuplication(MemberNicknameDuplReqDto dto){

        return BaseDataResponse.<MemberNicknameDuplResDto>builder()
                .result(memberService.checkMemberNickname(dto))
                .build();

    }

    @Operation(
            summary = "유저 닉네임 중복 검사",
            description = "유저의 닉네임 수정 시 완료버튼을 누르기 전 닉네임 중복검사"
    )
    @PatchMapping("/member/question-tf")
    public BaseResponse changeQuestionTF(@AuthenticationPrincipal Member member){
        memberService.changeQuestionTF(member);
        return new BaseResponse();

    }
}

package com.oppla.server.domain.member.controller;

import com.oppla.server.domain.member.dto.*;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.member.service.MemberService;
import com.oppla.server.global.common.response.BaseDataResponse;
import com.oppla.server.global.common.response.BaseResponse;
import com.oppla.server.global.common.response.PaginationResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Operation(
            summary = "*유저의 마이페이지 보기",
            description = "특정 유저의 마이페이지 보기" +
                    "\n 자신의 페이지인지 확인하기 위해 Member Id Token 필요"
    )
    @GetMapping("/{memberId}")
    public BaseDataResponse<MemberProfileResDto> getMemberProfile(@Parameter(hidden = true) @AuthenticationPrincipal Member member, @PathVariable("memberId") Long memberId){

        return BaseDataResponse.<MemberProfileResDto>builder()
                .result(memberService.forMemberProfile(member, memberId))
                .build();

    }

    @Operation(
            summary = "유저 프로필 수정",
            description = "유저의 닉네임과, 한줄 소개를 수정" +
                    "\n 닉네임과 한줄 소개 모두 데이터가 있어야햠." +
                    "\n 닉네임만 수정하는 경우 한줄 소개에는 Old 데이터가 와야함."
    )
    @PatchMapping("")
    public BaseResponse patchMemberProfile(@Parameter(hidden = true) @AuthenticationPrincipal Member member, @RequestBody MemberProfilePatchReqDto dto){
        memberService.changeMemberProfile(member, dto);
        return new BaseResponse();

    }

    @Operation(
            summary = "유저 닉네임 중복 검사 ",
            description = "유저의 닉네임 수정 시 완료버튼을 누르기 전 닉네임 중복검사"
    )
    @GetMapping("/nickname/duplication")
    public BaseDataResponse<MemberNicknameDuplResDto> checkMemberNicknameDuplication(@RequestBody MemberNicknameDuplReqDto dto){

        return BaseDataResponse.<MemberNicknameDuplResDto>builder()
                .result(memberService.checkMemberNickname(dto))
                .build();

    }

    @Operation(
            summary = "*유저의 질문 알림 여부를 변경",
            description = "유저가 질문 알림을 받는 상태를 수정" +
                    "\n Member Id Token 필요"
    )
    @PatchMapping("/question-tf")
    public BaseResponse changeQuestionTF(@Parameter(hidden = true) @AuthenticationPrincipal Member member){
        memberService.changeQuestionTF(member);
        return new BaseResponse();
    }

    @Operation(
            summary = "유저의 거래내역 조회",
            description = "유저의 포인트 거래내역 조회" +
                    "\n Member Id Token 필요" +
                    "\n Pagination 적용" +
                    "\n size: 한번에 받을 개수" +
                    "\n page: 0부터 시작"
    )
    @GetMapping("/point-record")
    public BaseDataResponse<PaginationResDto<MemberPointRecordResDto>> getMemberPointRecord(@Parameter(hidden = true) @AuthenticationPrincipal Member member, Pageable pageable){

        return BaseDataResponse.<PaginationResDto<MemberPointRecordResDto>>builder()
                .result(memberService.forMemberPointRecord(member, pageable))
                .build();
    }
    @Operation(
            summary = "유저의 현재 위치 설정",
            description = "유저의 현재 위치를 설정하는 기능" +
                    "\n Member Id Token 필요" +
                    "\n 등록되어 있는 정보가 있으면 변경, 없다면 등록."
    )
    @PatchMapping("/location")
    public BaseDataResponse<NowLocationResDto> patchMemberLocation(@Parameter(hidden = true) @AuthenticationPrincipal Member member,
                                                                   @RequestBody NowLocationReqDto dto){

        return BaseDataResponse.<NowLocationResDto>builder()
                .result(memberService.patchMemberLocation(member, dto))
                .build();
    }
}

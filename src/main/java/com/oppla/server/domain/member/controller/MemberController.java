package com.oppla.server.domain.member.controller;

import com.oppla.server.domain.member.dto.MemberProfileResDto;
import com.oppla.server.domain.member.entity.Member;
import com.oppla.server.domain.member.service.MemberService;
import com.oppla.server.global.common.response.BaseDataResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @Operation(
            summary = "*유저의 마이페이지 보기",
            description = "특정 유저의 마이페이지 보기" +
                    "\n 자신의 페이지인지 확인하기 위해 Member Id Token 필요"
    )
    @GetMapping("/member/{memberId}")
    public BaseDataResponse<MemberProfileResDto> getMemberProfile(@AuthenticationPrincipal Member member,  @PathVariable("memberId") Long memberId){

        return BaseDataResponse.<MemberProfileResDto>builder()
                .result(memberService.forMemberProfile(member, memberId))
                .build();

    }
}

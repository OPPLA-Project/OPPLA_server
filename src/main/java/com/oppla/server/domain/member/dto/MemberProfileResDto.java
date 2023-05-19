package com.oppla.server.domain.member.dto;

import com.oppla.server.domain.review.dto.AnswerSpeedResDto;
import com.oppla.server.domain.review.dto.InfoScoreResDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberProfileResDto {
    /**
     * 마이페이지를 렌더링하기 위한 사용자 정보 Dto
     */

    @Schema(description = "멤버의 이미지 URL")
    private String imgUrl;
    @Schema(description = "멤버의 nickName")
    private String nickname;
    @Schema(description = "멤버의 보유 포인트")
    private Integer point;
    @Schema(description = "멤버의 후기점수")
    private Integer reviewScore;
    @Schema(description = "멤버의 채택률")
    private Double selectionPercent;
    @Schema(description = "멤버의 한줄 소개")
    private String intro;
    @Schema(description = "멤버의 주요 답변 지역")
    private List<AnswerAreaResDto> mainAnswerArea;
    @Schema(description = "멤버의 후기 평가")
    private InfoScoreResDto infoScore;
    @Schema(description = "멤버의 답변 속도")
    private AnswerSpeedResDto answerSpeed;
    @Schema(description = "현재 멤버인지 확인")
    private Boolean isMine;

}

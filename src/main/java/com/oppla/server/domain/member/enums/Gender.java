package com.oppla.server.domain.member.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.oppla.server.domain.auth.dto.SnsType;
import lombok.Getter;

public enum Gender {
    MAN("man", "male", "M"),
    WOMAN("woman", "female", "F"),
    NONE("none", "none", "U");

    private final String gender;
    private final String kakaoGender;
    private final String naverGender;

    Gender (String gender, String kakaoGender, String naverGender) {
        this.gender = gender;
        this.kakaoGender = kakaoGender;
        this.naverGender = naverGender;
    }

    @JsonCreator
    public static Gender from(String value) {

        for (Gender gender : Gender.values()) {
            if (gender.getGender().equals(value)) {
                return gender;
            }
        }
        return Gender.NONE;
    }

    @JsonCreator
    public static Gender from(String value, SnsType snsType) {
        if (snsType == SnsType.KAKAO) {
            for (Gender gender : Gender.values()) {
                if (gender.getKakaoGender().equals(value)) {
                    return gender;
                }
            }
        } else if (snsType == SnsType.NAVER) {
            for (Gender gender : Gender.values()) {
                if (gender.getNaverGender().equals(value)) {
                    return gender;
                }
            }
        }

        return Gender.NONE;
    }

    @JsonValue
    public String getGender() {
        return gender;
    }

    @JsonValue
    public String getKakaoGender() {
        return kakaoGender;
    }

    @JsonValue
    public String getNaverGender() {
        return naverGender;
    }
}

package com.oppla.server.domain.member.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum Gender {
    MAN("man"),
    WOMAN("woman"),
    NONE("none");

    @Getter
    private final String gender;

    Gender (String gender) {
        this.gender = gender;
    }

    @JsonCreator
    public static Gender from(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.getGender().equals(value)) {
                return gender;
            }
        }
        return null;
    }

    @JsonValue
    public String getGender() {
        return gender;
    }
}

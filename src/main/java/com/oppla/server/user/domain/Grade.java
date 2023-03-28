package com.oppla.server.user.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long gradeId;

    @Column(name = "grade_img")
    private String gradeImg;

    @OneToMany(mappedBy = "grade")
    private List<User> userList = new ArrayList<>();
}

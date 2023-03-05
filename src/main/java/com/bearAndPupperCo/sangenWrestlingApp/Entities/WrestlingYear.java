package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class WrestlingYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestlingYearId;

    private Integer wrestlingYear;

    @OneToMany(mappedBy="wrestlingYear")
    List<PPVMonth> ppvMonthList;

    @ManyToMany(mappedBy = "wrestlingYear")
    private List<OtherAchievement> otherAchievementList;



}

package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class OtherAchievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer otherAchievementsId;

    private String achievementsName;

    @ManyToMany
    @JoinTable(name = "other_achievement_wrestling_year", joinColumns = @JoinColumn(name = "other_achievements_id"), inverseJoinColumns = @JoinColumn(name = "wrestling_year_id"))
    private List<WrestlingYear> wrestlingYear;

    @ManyToOne
    @JoinColumn(name="ppv_month_id")
    private PPVMonth ppvMonth;


}

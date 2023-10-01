package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class PPVMonth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ppvMonthId;

    private String ppvMonthName;
    @ManyToOne
    @JoinColumn(name="wrestling_year_id")
    private WrestlingYear wrestlingYear;

    @ManyToMany
    @JoinTable(name = "ppv_month_other_achievement", joinColumns = @JoinColumn(name = "ppv_month_id"), inverseJoinColumns = @JoinColumn(name = "other_achievements_id"))
    private List<OtherAchievement> otherAchievements;

}

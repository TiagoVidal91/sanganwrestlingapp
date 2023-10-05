package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class PPVMonth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ppvMonthId;

    private String ppvName;

    @OneToMany(mappedBy="ppvMonth")
    private List<WrestlingShow> wrestlingShows;

    @ManyToOne
    @JoinColumn(name="wrestling_year_id")
    private WrestlingYear wrestlingYear;

    @ManyToMany
    @JoinTable(name = "ppv_month_other_achievement", joinColumns = @JoinColumn(name = "ppv_month_id"), inverseJoinColumns = @JoinColumn(name = "other_achievements_id"))
    private List<OtherAchievement> otherAchievements;

}

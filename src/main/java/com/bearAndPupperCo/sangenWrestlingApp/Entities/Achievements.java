package com.bearAndPupperCo.sangenWrestlingApp.Entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Achievements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer achievementsId;

    @ManyToMany
    @JoinTable(name = "achievements_wrestling_match", joinColumns = @JoinColumn(name = "achievements_id"), inverseJoinColumns = @JoinColumn(name = "wrestling_match_id"))
    private List<WrestlingMatch> fiveStarMatches;

    @ManyToMany
    @JoinTable(name = "achievements_wrestling_award", joinColumns = @JoinColumn(name = "achievements_id"), inverseJoinColumns = @JoinColumn(name = "wrestling_award_id"))
    private List<WrestlingAward> wrestlingAwards;

    @ManyToMany
    @JoinTable(name = "achievements_other_achievement", joinColumns = @JoinColumn(name = "achievements_id"), inverseJoinColumns = @JoinColumn(name = "other_achievements_id"))
    private List<OtherAchievement> otherAchievements;

}

package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Achievements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer achievementsId;

    @ManyToMany
    @JoinColumn(name = "wrestling_match_id")
    List<WrestlingMatch> fiveStarMatches;

    @ManyToMany
    @JoinColumn(name = "wrestling_award_id")
    List<WrestlingAward> wrestlingAwards;

    @ManyToMany
    @JoinColumn(name = "other_achievements_id")
    List<OtherAchievement> otherAchievements;

}

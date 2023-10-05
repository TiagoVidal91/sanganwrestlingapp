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
    @JoinColumn(name="wrestling_match_id")
    private List<WrestlingMatch> fiveStarMatches;

    @ManyToMany
    @JoinColumn(name = "wrestling_award_id")
    private List<WrestlingAward> wrestlingAwards;

    @ManyToMany
    @JoinColumn(name = "other_achievements_id")
    private List<OtherAchievement> otherAchievements;

}

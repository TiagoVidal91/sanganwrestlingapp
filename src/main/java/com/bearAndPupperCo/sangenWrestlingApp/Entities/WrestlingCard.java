package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class WrestlingCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wrestlingCardId;

    @OneToOne(mappedBy = "wrestlingCard")
    private Wrestler wrestler;

    @ManyToMany
    @JoinColumn(name = "tag_team_id")
    List<TagTeam> wrestlerTeams;

    @ManyToMany
    @JoinColumn(name = "achievement_id")
    List<Achievements> wrestlerAchievements;

    @OneToMany(mappedBy="wrestlingTitle")
    List<TitleReign> wrestlerTitleReigns;

}

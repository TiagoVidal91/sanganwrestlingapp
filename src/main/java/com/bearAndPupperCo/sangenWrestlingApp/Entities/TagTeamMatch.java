package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "wrestling_match_id")
public class TagTeamMatch extends WrestlingMatch{

    @ManyToMany
    @JoinColumn(name="tag_team_id")
    List<TagTeam> participants;
    @ManyToMany
    @JoinColumn(name="tag_team_id")
    List<TagTeam> winners;
    @ManyToMany
    @JoinColumn(name="tag_team_id")
    List<TagTeam> losers;
    @ManyToMany
    @JoinColumn(name="tag_team_id")
    List<TagTeam> draw;
    @ManyToMany
    @JoinColumn(name="tag_team_id")
    List<TagTeam> noWin;


}

package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MapKeyJoinColumn;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "title_reign_id")
public class TagTeamTitleReign extends TitleReign{

    @OneToOne
    @MapsId
    private TitleReign titleReign;

    @OneToOne
    private TagTeam wonAgainst;

    @ManyToMany
    @JoinColumn(name="tag_team_id")
    private List<TagTeam> defendedFrom;

    @OneToOne
    private TagTeam lostAgainst;

}

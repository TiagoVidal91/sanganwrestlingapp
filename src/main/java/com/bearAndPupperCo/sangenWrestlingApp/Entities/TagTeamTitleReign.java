package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "titleReignId")
public class TagTeamTitleReign extends TitleReign {

    @OneToOne
    @MapsId
    private TitleReign titleReign;

    @OneToOne
    private TagTeam wonAgainst;

    @ElementCollection
    @MapKeyJoinColumn(name = "tagTeamId")  // assuming TagTeam has an 'id' field
    private List<TagTeam> defendedFrom;

    @OneToOne
    private TagTeam lostAgainst;

}

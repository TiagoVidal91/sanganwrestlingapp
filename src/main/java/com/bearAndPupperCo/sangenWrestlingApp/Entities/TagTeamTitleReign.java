package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "titleReignId")
public class TagTeamTitleReign {

    private TagTeam wonAgainst;

    private List<TagTeam> defendedFrom;

    private TagTeam lostAgainst;

}

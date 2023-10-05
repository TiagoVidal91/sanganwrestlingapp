package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
public class TagTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tagTeamId;

    private String tagTeamName;

    @ManyToMany(mappedBy = "wrestlerTeams")
    List<WrestlingCard> wrestlers;




}

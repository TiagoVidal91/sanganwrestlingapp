package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "titleReignId")
public class SingleTitleReign extends TitleReign{

    private Wrestler wonAgainst;

    private List<Wrestler> defendedFrom;

    private Wrestler lostAgainst;

}

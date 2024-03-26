package com.bearAndPupperCo.sangenWrestlingApp.Entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "wrestling_match_id")
public class SingleMatch extends WrestlingMatch{

    @ManyToMany
    @JoinColumn(name="wrestler_id")
    List<Wrestler> participants;
    @ManyToMany
    @JoinColumn(name="wrestler_id")
    List<Wrestler> winners;
    @ManyToMany
    @JoinColumn(name="wrestler_id")
    List<Wrestler> losers;
    @ManyToMany
    @JoinColumn(name="wrestler_id")
    List<Wrestler> draw;
    @ManyToMany
    @JoinColumn(name="wrestler_id")
    List<Wrestler> noWin;


}

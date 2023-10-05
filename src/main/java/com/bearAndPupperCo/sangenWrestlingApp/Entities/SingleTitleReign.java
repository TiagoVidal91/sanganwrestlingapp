package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "title_reign_id")
public class SingleTitleReign extends TitleReign{

    @OneToOne
    @MapsId
    private TitleReign titleReign;

    @OneToOne
    private Wrestler wonAgainst;

    @ManyToMany
    @JoinTable(
            name = "single_title_reign_defended_from",
            joinColumns = @JoinColumn(name = "title_reign_id"),
            inverseJoinColumns = @JoinColumn(name = "wrestler_id")
    )
    private List<Wrestler> defendedFrom;

    @OneToOne
    private Wrestler lostAgainst;

}

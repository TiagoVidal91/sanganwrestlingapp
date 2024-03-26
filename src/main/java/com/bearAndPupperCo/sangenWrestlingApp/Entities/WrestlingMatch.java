package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import com.bearAndPupperCo.sangenWrestlingApp.Enum.WrestlingMatchTypeEnum;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class WrestlingMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestlingMatchId;

    @ManyToOne
    @JoinColumn(name="wrestling_show_id")
    private WrestlingShow wrestlingShow;

    private double rating;

    private String comment;

    @ManyToMany(mappedBy = "wrestlingMatchesList")
    private List<Wrestler> opponents;

    @ManyToMany(mappedBy = "matchVictories")
    private List<Wrestler> winner;

    @ManyToMany(mappedBy = "matchLosses")
    private List<Wrestler> loser;

    private Boolean isDraw;

    @ManyToMany(mappedBy = "matchInterference")
    private List<Wrestler> interference;

    private Boolean isTitleMatch;

    @ManyToOne
    @JoinColumn(name="wrestling_title_id")
    private WrestlingTitle wrestlingTitle;

    private Boolean isMoneyInTheBankCashIn;

}

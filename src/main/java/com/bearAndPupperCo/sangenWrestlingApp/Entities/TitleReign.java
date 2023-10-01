package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TitleReign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long titleReignId;

    @ManyToOne
    @JoinColumn(name = "wrestling_title_id")
    private WrestlingTitle wrestlingTitle;

    @ManyToOne
    @JoinColumn(name = "starting_year_id")
    private WrestlingYear startingYear;

    @ManyToOne
    @JoinColumn(name = "losing_year_id")
    private WrestlingYear losingYear;

    @ManyToOne
    @JoinColumn(name = "starting_month_id")
    private PPVMonth startingMonth;

    @ManyToOne
    @JoinColumn(name = "losing_month_id")
    private PPVMonth losingMonth;
}

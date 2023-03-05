package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TitleReign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long titleReignId;

    @ManyToOne
    @JoinColumn(name="wrestling_title_id")
    private WrestlingTitle wrestlingTitle;

    @ManyToOne
    @JoinColumn(name="wrestling_year_id")
    private WrestlingYear startingYear;

    @ManyToOne
    @JoinColumn(name="wrestling_year_id")
    private WrestlingYear losingYear;

    @ManyToOne
    @JoinColumn(name="ppv_month_id")
    private PPVMonth startingMonth;

    @ManyToOne
    @JoinColumn(name="ppv_month_id")
    private PPVMonth losingMonth;

}

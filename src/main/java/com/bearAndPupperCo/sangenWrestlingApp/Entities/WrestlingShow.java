package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class WrestlingShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestlingShowId;

    @OneToMany(mappedBy = "wrestlingShow")
    private List<WrestlingMatch> wrestlingMatchList;

    @ManyToOne
    @JoinColumn(name = "wrestling_month_id")
    private WrestlingMonth wrestlingMonth;

    @ManyToOne
    @JoinColumn(name = "wrestling_week_id")
    private WrestlingWeek wrestlingWeek;

    @ManyToOne
    @JoinColumn(name="wrestling_brand_id")
    private WrestlingBrand wrestlingBrand;

    @ManyToOne
    @JoinColumn(name = "wrestling_year_id")
    private WrestlingYear wrestlingYear;

    private Boolean isPPV;


}

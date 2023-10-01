package com.bearAndPupperCo.sangenWrestlingApp.Entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Wrestler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wrestlerId;

    @Column(columnDefinition = "VARCHAR(255)")
    private String wrestlerName;

    @Column(columnDefinition = "VARCHAR(255)")
    private String inRingName;

    @ManyToOne
    @JoinColumn(name="wrestling_year_id")
    private WrestlingYear startingYear;

    @Column(columnDefinition = "VARCHAR(255)")
    private String signatureMoves;

    @Column(columnDefinition = "VARCHAR(255)")
    private String finishers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wrestling_card_id")
    private WrestlingCard wrestlingCard;

    @Column(columnDefinition = "VARCHAR(255)")
    private String wrestlerPicPath;

    @ManyToOne
    @JoinColumn(name="wrestling_brand_id")
    private WrestlingBrand wrestlingBrand;

    @ManyToMany(mappedBy = "defendedFrom")
    private List<SingleTitleReign> titleReignsDefended;

    @OneToOne(mappedBy = "lostAgainst")
    private SingleTitleReign lostTitle;

    @OneToOne(mappedBy = "wonAgainst")
    private SingleTitleReign wonTitle;


}

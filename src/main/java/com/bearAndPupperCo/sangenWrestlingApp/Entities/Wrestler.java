package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Wrestler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestlerId;

    @Column(columnDefinition = "VARCHAR(255)")
    private String wrestlerName;

    @Column(columnDefinition = "VARCHAR(255)")
    private String inRingName;

    @ElementCollection
    @CollectionTable(name="nicknames", joinColumns=@JoinColumn(name="wrestler_Id"))
    @Column(name="nickname")
    private List<String> nicknames;

    @ElementCollection
    @CollectionTable(name="signatureMoves", joinColumns=@JoinColumn(name="wrestler_Id"))
    @Column(name="signatureMoves")
    private List<String> signatureMoves;

    @ElementCollection
    @CollectionTable(name="finishers", joinColumns=@JoinColumn(name="wrestler_Id"))
    @Column(name="finishers")
    private List<String> finishers;

    @Column(columnDefinition = "VARCHAR(255)")
    private String wrestlerPicPath;

    @ManyToOne
    @JoinColumn(name="wrestling_brand_id")
    private WrestlingBrand wrestlingBrand;

    @ManyToOne
    @JoinColumn(name="wrestling_locker_room_id")
    private LockerRoom wrestlingLockerRoom;

    @ManyToMany
    private List<WrestlingMatch> wrestlingMatchesList;

    @ManyToMany
    private List<WrestlingMatch> matchVictories;

    @ManyToMany
    private List<WrestlingMatch> matchLosses;

    @ManyToMany
    private List<WrestlingMatch> matchInterference;

    @ManyToMany(mappedBy = "wrestlerList")
    private List<WrestlingTitle> wrestlingTitleList;

}

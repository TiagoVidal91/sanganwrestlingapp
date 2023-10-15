package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class WrestlingTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestling_title_id;

    private String titleName;

    @ManyToMany
    private List<Wrestler> wrestlerList;

    @OneToMany(mappedBy = "wrestlingTitle")
    private List<WrestlingMatch> wrestlingMatches;

}

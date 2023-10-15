package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class WrestlingYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestlingYearId;

    private String wrestlingYearName;

    @OneToMany(mappedBy = "wrestlingYear")
    List<WrestlingShow> wrestlingShows;
}

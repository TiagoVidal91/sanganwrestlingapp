package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class WrestlingMonth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestlingMonthId;

    private String wrestlingMonthName;

    @OneToMany(mappedBy = "wrestlingMonth")
    List<WrestlingShow> wrestlingShows;

}

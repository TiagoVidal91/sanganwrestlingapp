package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class WrestlingWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestlingWeekId;

    private String wrestlingWeekName;

    @OneToMany(mappedBy = "wrestlingWeek")
    List<WrestlingShow> wrestlingShows;
}

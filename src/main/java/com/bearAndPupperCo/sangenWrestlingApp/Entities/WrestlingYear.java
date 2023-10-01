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

    private Integer wrestlingYear;

    @OneToMany(mappedBy = "wrestlingYear", cascade = CascadeType.ALL)
    private List<PPVMonth> ppvMonthList;

    /*@OneToMany(mappedBy = "startingYear", cascade = CascadeType.ALL)
    private List<Wrestler> wrestlers;*/

    @ManyToMany(mappedBy = "wrestlingYear")
    private List<OtherAchievement> otherAchievementList;
}

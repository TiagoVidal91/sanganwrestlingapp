package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class OtherAchievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer otherAchievementsId;

    private String achievementsName;

    @ManyToMany
    @JoinColumn(name="wrestling_year_id")
    private List<WrestlingYear> wrestlingYear;

    @ManyToOne
    @JoinColumn(name="ppv_month_id")
    private PPVMonth ppvMonth;


}

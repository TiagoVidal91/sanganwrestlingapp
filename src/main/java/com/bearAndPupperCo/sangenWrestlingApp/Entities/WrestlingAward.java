package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class WrestlingAward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wrestlingAwardsId;

    private String awardName;

    @ManyToOne
    @JoinColumn(name="wrestling_year_id")
    private WrestlingYear wrestlingYear;

    private Integer awardsType;

}

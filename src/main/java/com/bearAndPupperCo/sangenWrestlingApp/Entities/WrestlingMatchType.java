package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class WrestlingMatchType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestlingMatchTypeId;

    private String matchTypeName;

    @OneToMany(mappedBy="wrestlingMatchType")
    List<WrestlingMatch> wrestlingMatches;

}

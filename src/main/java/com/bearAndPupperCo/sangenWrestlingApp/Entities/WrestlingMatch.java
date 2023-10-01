package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class WrestlingMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestlingMatchId;

    @ManyToOne
    @JoinColumn(name="wrestling_match_type_id")
    private WrestlingMatchType wrestlingMatchType;



}

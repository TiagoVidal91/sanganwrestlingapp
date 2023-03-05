package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Wrestler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wrestlerId;

    private String wrestlerName;

    private String inRingName;

    private WrestlingYear startingYear;

    private String signatureMoves;

    private String finishers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wrestling_card_id")
    private WrestlingCard wrestlingCard;

    private String wrestlerPicPath;


}

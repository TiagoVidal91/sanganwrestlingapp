package com.bearAndPupperCo.sangenWrestlingApp.DTO;


import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingTitle;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WrestlerDTO {

    private Long wrestlerId;

    private String wrestlerName;

    private String inRingName;

    private List<String> nicknames;

    private List<String> signatureMoves;

    private List<String> finishers;

    private Long brandId;

    private Long lockerId;

    private Integer numberOfWins;

    private Integer numberOfLosses;

    private Integer numberOfMatches;

    private Integer wrestlingStreak;

    private List<WrestlingTitle> wrestlingTitleList;

}

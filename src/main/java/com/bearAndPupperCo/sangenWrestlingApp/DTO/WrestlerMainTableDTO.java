package com.bearAndPupperCo.sangenWrestlingApp.DTO;


import com.bearAndPupperCo.sangenWrestlingApp.Entities.LockerRoom;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingBrand;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingTitle;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WrestlerMainTableDTO {

    private String inRingName;

    private LockerRoom lockerRoom;

    private WrestlingBrand brand;

    private Integer numberOfWins;

    private Integer numberOfLosses;

    private Integer wrestlingStreak;

    private double percentageOfWins;

}

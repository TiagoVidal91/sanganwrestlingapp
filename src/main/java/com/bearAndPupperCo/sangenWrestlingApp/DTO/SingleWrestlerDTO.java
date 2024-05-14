package com.bearAndPupperCo.sangenWrestlingApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SingleWrestlerDTO {

    private Long wrestlerId;

    private String wrestlerName;

    private String inRingName;

    private Integer age;

    private String hometown;

    private String weight;

    private String height;

    private String periodOfArrival;

    private List<String> nicknames;

    private List<String> signatureMoves;

    private List<String> finishers;

    private String wrestlerPicPath;

    private Long wrestlingBrandId;

    private Long wrestlingLockerRoomId;

    private Integer wrestlingStreak;

}

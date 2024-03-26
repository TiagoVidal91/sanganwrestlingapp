package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class WrestlingTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestlingTitleId;

    private String titleName;

    @ManyToMany
    private List<Wrestler> wrestlerList;

    @OneToMany(mappedBy = "wrestlingTitle")
    private List<WrestlingMatch> wrestlingMatches;

    @ManyToOne
    @JoinColumn(name = "wrestling_locker_room_id")
    private LockerRoom lockerRoom;

}

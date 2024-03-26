package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LockerRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestlingLockerRoomId;

    private String wrestlingLockerRoomName;

    @OneToMany(mappedBy="wrestlingLockerRoom")
    List<Wrestler> wrestlers;

    @OneToMany(mappedBy = "lockerRoom")
    List<WrestlingTitle> wrestlingTitles;

}

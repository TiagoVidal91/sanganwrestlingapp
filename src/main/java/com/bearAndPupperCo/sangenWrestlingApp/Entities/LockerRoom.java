package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class LockerRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestlingLockerRoomId;

    private String wrestlingLockerRoomName;

    @OneToMany(mappedBy="wrestlingLockerRoom")
    List<Wrestler> wrestlers;

}

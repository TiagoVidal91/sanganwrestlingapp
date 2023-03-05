package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class WrestlingTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestlingTitleId;

    private String titleName;

    private String titleType;

    private String titleSex;

    @OneToMany(mappedBy="wrestlingTitle")
    List<TitleReign> titleReigns;

}

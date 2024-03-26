package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import com.bearAndPupperCo.sangenWrestlingApp.Enum.ShowTypeEnum;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class WrestlingMonth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestlingMonthId;

    private String wrestlingMonthName;

    @OneToMany(mappedBy = "wrestlingMonth")
    List<WrestlingShow> wrestlingShows;

}

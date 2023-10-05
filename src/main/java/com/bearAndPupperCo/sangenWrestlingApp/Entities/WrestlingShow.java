package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import com.bearAndPupperCo.sangenWrestlingApp.Enum.ShowTypeEnum;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class WrestlingShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wrestlingShowId;

    @ManyToOne
    @JoinColumn(name="ppv_month_id")
    private PPVMonth ppvMonth;

    private ShowTypeEnum showTypeEnum;

    @OneToMany(mappedBy="wrestlingShow")
    List<WrestlingMatch> wrestlingMatches;

}

package com.bearAndPupperCo.sangenWrestlingApp.Entities;

import com.bearAndPupperCo.sangenWrestlingApp.Enum.WrestlingMatchTypeEnum;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class WrestlingMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestlingMatchId;

    @ManyToOne
    @JoinColumn(name="wrestling_show_id")
    private WrestlingShow wrestlingShow;

    private WrestlingMatchTypeEnum wrestlingMatchTypeEnum;


}

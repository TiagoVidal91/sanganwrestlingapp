package com.bearAndPupperCo.sangenWrestlingApp.Entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class WrestlingBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestlingBrandId;

    private String wrestlingBrandName;

    @OneToMany(mappedBy="wrestlingBrand")
    List<Wrestler> wrestlers;

}

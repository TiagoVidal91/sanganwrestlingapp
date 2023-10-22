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
public class WrestlingBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wrestlingBrandId;

    private String wrestlingBrandName;

    @OneToMany(mappedBy="wrestlingBrand")
    List<Wrestler> wrestlers;

}

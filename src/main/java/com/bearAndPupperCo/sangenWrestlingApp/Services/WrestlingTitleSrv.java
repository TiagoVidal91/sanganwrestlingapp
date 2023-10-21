package com.bearAndPupperCo.sangenWrestlingApp.Services;

import com.bearAndPupperCo.sangenWrestlingApp.Entities.LockerRoom;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingTitle;

import java.util.List;

public interface WrestlingTitleSrv {

    WrestlingTitle addWrestlingTitle(WrestlingTitle wrestlingTitle);

    List<WrestlingTitle> addWrestlingTitles(List<WrestlingTitle> wrestlingTitles);

}

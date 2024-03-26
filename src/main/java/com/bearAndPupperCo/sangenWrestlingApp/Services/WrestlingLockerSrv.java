package com.bearAndPupperCo.sangenWrestlingApp.Services;

import com.bearAndPupperCo.sangenWrestlingApp.Entities.LockerRoom;
import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingBrand;

import java.util.List;

public interface WrestlingLockerSrv {

   LockerRoom addWrestlingLocker(LockerRoom lockerRoom);

    List<LockerRoom> addWrestlingLockers(List<LockerRoom> wrestlingLockers);
}

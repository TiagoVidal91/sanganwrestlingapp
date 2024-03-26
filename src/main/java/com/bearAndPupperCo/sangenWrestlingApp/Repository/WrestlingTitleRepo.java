package com.bearAndPupperCo.sangenWrestlingApp.Repository;

import com.bearAndPupperCo.sangenWrestlingApp.Entities.WrestlingTitle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.Transient;

public interface WrestlingTitleRepo extends JpaRepository<WrestlingTitle, Long> {

    WrestlingTitle findWrestlingTitleByWrestlingTitleId(Long wrestlingTitleId);

}

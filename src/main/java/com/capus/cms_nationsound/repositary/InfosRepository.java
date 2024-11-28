package com.capus.cms_nationsound.repositary;

import com.capus.cms_nationsound.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfosRepository  extends JpaRepository<Information,Long> {
}

package com.capus.cms_nationsound.service;

import com.capus.cms_nationsound.entity.Information;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InfosService {
    Information createInformation(Information information);
    Information deleteInformation(Long id);

    List<Information> getAllInformation();

    Information update(Long id,Information request);
}

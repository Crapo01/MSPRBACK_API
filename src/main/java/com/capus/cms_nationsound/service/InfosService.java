package com.capus.cms_nationsound.service;

import com.capus.cms_nationsound.entity.Information;
import com.capus.cms_nationsound.exceptions.ApiException;

import java.util.List;

public interface InfosService {
    Information createInformation(Information information);
    Information deleteInformation(Long id) throws ApiException;

    List<Information> getAllInformation();

    Information update(Long id,Information request) throws ApiException;
}

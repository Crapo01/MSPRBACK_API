package com.capus.cms_nationsound.service;

import com.capus.cms_nationsound.entity.Information;
import com.capus.cms_nationsound.repositary.InfosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InfosServiceImpl implements InfosService {

    private InfosRepository infosRepository;

    @Override
    public Information createInformation(Information information) {
        return infosRepository.save(information);
    }
}

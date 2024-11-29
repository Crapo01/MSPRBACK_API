package com.capus.cms_nationsound.service;

import com.capus.cms_nationsound.entity.Information;
import com.capus.cms_nationsound.exceptions.ApiException;
import com.capus.cms_nationsound.repository.InfosRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class InfosServiceImpl implements InfosService {


    private InfosRepository infosRepository;

    @Override
    public Information createInformation(Information information) {
         return infosRepository.save(information);
    }

    @Override
    public Information deleteInformation(Long id) throws ApiException {
        Information informationDeleted =infosRepository.findById(id).orElseThrow(()->new ApiException("Id:"+id+" Not found in database", HttpStatus.NOT_FOUND));
        infosRepository.deleteById(id);
        return informationDeleted;
    }

    @Override
    public List<Information> getAllInformation() {
            return infosRepository.findAll();
    }

    @Override
    public Information update(Long id,Information request) throws ApiException {
        Information information = infosRepository
                .findById(id)
        //          .orElseThrow(()->new ResourceNotFoundException("Id:"+id+" Not found in database"));
        .orElseThrow(()->new ApiException("Id:"+id+" Not found in database", HttpStatus.NOT_FOUND));

        information.setId(id);
        information.setMessage(request.getMessage());
        information.setImportant(request.isImportant());
        return  infosRepository.save(information);
    }
}

package com.capus.cms_nationsound.service;

import com.capus.cms_nationsound.entity.Information;
import com.capus.cms_nationsound.exceptions.ResourceNotFoundException;
import com.capus.cms_nationsound.repositary.InfosRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.capus.cms_nationsound.dto.responses.MyHttpResponse.response;

@Service
@AllArgsConstructor
public class InfosServiceImpl implements InfosService {

    private InfosRepository infosRepository;

    @Override
    public Information createInformation(Information information) {
         return infosRepository.save(information);
    }

    @Override
    public Information deleteInformation(Long id) {
//        if(infosRepository.existsById(id)) {
//            infosRepository.deleteById(id);
//            return " successfully deleted information with id " + id;
//        }else {
//            return "Information not found with id: " + id;
//        }
        Information informationDeleted =infosRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Id:"+id+" Not found in database"));
        infosRepository.deleteById(id);
        return informationDeleted;
    }

    @Override
    public List<Information> getAllInformation() {
            return infosRepository.findAll();
    }

    @Override
    public Information update(Long id,Information request) {
        Information information = infosRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Id:"+id+" Not found in database"));
        information.setId(id);
        information.setMessage(request.getMessage());
        information.setImportant(request.isImportant());
        return  infosRepository.save(information);
    }
}

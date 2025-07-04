package com.capus.securedapi.service.impl;


import com.capus.securedapi.entity.Pointer;
import com.capus.securedapi.exceptions.ApiException;
import com.capus.securedapi.repository.PointeurRepository;
import com.capus.securedapi.service.PointeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointeurServiceIplm implements PointeurService {

    @Autowired
    private PointeurRepository pointeurRepository;


    @Override
    public Pointer createPointeur(Pointer pointeur) {
        return pointeurRepository.save(pointeur);
    }

    @Override
    public Pointer deletePointeur(Long id) throws ApiException {
        Pointer pointeurDeleted = pointeurRepository.findById(id).orElseThrow(() -> new ApiException("Id:" + id + " Not found in database", HttpStatus.NOT_FOUND));
        pointeurRepository.deleteById(id);
        return pointeurDeleted;
    }

    @Override
    public List<Pointer> getAllPointeurs() {
        return pointeurRepository.findAll();
    }

    @Override
    public Pointer update(Long id, Pointer request) throws ApiException {
        Pointer pointeur = pointeurRepository
                .findById(id)
                .orElseThrow(() -> new ApiException("Id:" + id + " Not found in database", HttpStatus.NOT_FOUND));
        pointeur.setId(id);
        pointeur.setName(request.getName());
        pointeur.setLat(request.getLat());
        pointeur.setLon(request.getLon());
        pointeur.setType(request.getType());
        pointeur.setDescription(request.getDescription());
        pointeur.setLink(request.getLink());
        return pointeurRepository.save(pointeur);

    }
}

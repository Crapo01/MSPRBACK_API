package com.capus.securedapi.service;

import com.capus.securedapi.entity.Pointer;
import com.capus.securedapi.exceptions.ApiException;

import java.util.List;

public interface PointeurService {
    Pointer createPointeur(Pointer pointeur);
    Pointer deletePointeur(Long id) throws ApiException;

    List<Pointer> getAllPointeurs();

    Pointer update(Long id, Pointer request) throws ApiException;
}

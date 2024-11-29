package com.capus.cms_nationsound.controller;

import com.capus.cms_nationsound.entity.Information;
import com.capus.cms_nationsound.exceptions.ApiException;
import com.capus.cms_nationsound.service.InfosService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.capus.cms_nationsound.dto.responses.CustomHttpResponse.response;


@AllArgsConstructor
@RestController
@RequestMapping("/api/infos/")
public class InfosController {

    private final InfosService infosService;

    @GetMapping("all")
    public ResponseEntity<Object> getAllInformation() {
        List<Information> informations = infosService.getAllInformation();
        return response(HttpStatus.OK,"All Informations returned",informations);
    }

    @PostMapping()
    public ResponseEntity<Object> createInfo(@Valid @RequestBody Information information) {
        Information createdInformation =infosService.createInformation(information);
        return response(HttpStatus.CREATED,"Information created",createdInformation);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Information request) throws ApiException {

        Information updatedInformation = infosService.update(id,request);
        return response(HttpStatus.CREATED,"Information updated",updatedInformation);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteInfo(@PathVariable Long id) throws ApiException {
        return response(HttpStatus.OK,"Information deleted",infosService.deleteInformation(id));
    }
}

package com.capus.cms_nationsound.controller;

import com.capus.cms_nationsound.dto.responses.HttpResponse;
import com.capus.cms_nationsound.entity.Information;
import com.capus.cms_nationsound.service.InfosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/infos/")
public class InfosController {

    private final InfosService infosService;
    @PostMapping()
    public ResponseEntity<?> createInfo(@RequestBody Information information) {
        return ResponseEntity.ok(infosService.createInformation(information));
    }
}

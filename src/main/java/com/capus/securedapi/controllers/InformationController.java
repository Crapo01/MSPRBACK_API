package com.capus.securedapi.controllers;

import com.capus.securedapi.entity.Information;
import com.capus.securedapi.exceptions.ApiException;
import com.capus.securedapi.repository.InformationRepository;
import com.capus.securedapi.service.InformationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.capus.securedapi.payload.response.CustomHttpResponse.response;

@Tag(name = "Infos", description = "Infos APIs")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/informations/")
public class InformationController {

    @Autowired
    private final InformationService informationService;
    @Autowired
    private final InformationRepository informationRepository;

    public InformationController(InformationService informationService, InformationRepository informationRepository) {
        this.informationService = informationService;
        this.informationRepository = informationRepository;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Information.class)) })
    })
    @Operation(
            summary = "Create a new info",
            description = "ONLY FOR EDITORS.",
            tags = { "Editor only" })
    @PostMapping
    @PreAuthorize("hasRole('EDITOR')")
    public ResponseEntity<Object> createInfo(@Valid @RequestBody Information information) {
        Information createdInformation =informationService.createInformation(information);
        return response(HttpStatus.CREATED,"Information created",createdInformation);
    }


    @Operation(
            summary = "Get all infos in DB",
            description = "All access allowed.",
            tags = { "All access allowed" })
    @GetMapping("all")
    // ALL authorized
    public ResponseEntity<List<Information>> getAllInformation() {
        List<Information> informations = informationService.getAllInformation();
        //return response(HttpStatus.OK,"All Informations returned",informations);
        return ResponseEntity.ok(informations);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Information.class)) }),
            @ApiResponse(responseCode = "400", description = "No Information found with id: +id")
    })
    @Operation(
            summary = "Update an info by ID",
            description = "ONLY FOR EDITORS.",
            tags = { "Editor only" })
    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('EDITOR')")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Information request) throws ApiException {

        Information updatedInformation = informationService.update(id,request);
        return response(HttpStatus.CREATED,"Information updated",updatedInformation);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Deleted Information "),
            @ApiResponse(responseCode = "400", description = "No Information found with id: +id")
    })
    @Operation(
            summary = "Delete an info by ID",
            description = "ONLY FOR EDITORS.",
            tags = { "Editor only" })
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
    public ResponseEntity<Object> deleteInfo(@PathVariable Long id) throws ApiException {
        return response(HttpStatus.OK,"Information deleted",informationService.deleteInformation(id));
    }
}

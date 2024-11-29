package com.kauanProjects.projetoAplicado.controllers;

import com.kauanProjects.projetoAplicado.dtos.EcopointDTO;
import com.kauanProjects.projetoAplicado.dtos.EcopointResponseDTO;
import com.kauanProjects.projetoAplicado.services.EcopointManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/ecopoint")
public class EcopointRestController {

    private final EcopointManagementService ecopointManagementService;

    @Autowired
    public EcopointRestController(EcopointManagementService ecopointManagementService) {
        this.ecopointManagementService = ecopointManagementService;
    }


    @PostMapping
    public ResponseEntity<EcopointResponseDTO> callEcopointManagementServiceForCreateEcopoint
            (@RequestBody EcopointDTO ecopointDTO) {
        EcopointResponseDTO ecopointResponseDTO = ecopointManagementService.createEcopoint(ecopointDTO);

        return ResponseEntity
                .created(createUriLocationFromCurrentRequest(ecopointResponseDTO))
                .body(ecopointResponseDTO);
    }


    private URI createUriLocationFromCurrentRequest(EcopointResponseDTO ecopointResponseDTO) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ecopointResponseDTO.getId())
                .toUri();
    }
}

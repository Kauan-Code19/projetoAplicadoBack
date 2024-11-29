package com.kauanProjects.projetoAplicado.controllers;

import com.kauanProjects.projetoAplicado.dtos.DistrictDTO;
import com.kauanProjects.projetoAplicado.dtos.DistrictResponseDTO;
import com.kauanProjects.projetoAplicado.services.DistrictManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/district")
public class DistrictRestController {

    private final DistrictManagementService districtManagementService;

    @Autowired
    public DistrictRestController(DistrictManagementService districtManagementService) {
        this.districtManagementService = districtManagementService;
    }


    @PostMapping
    public ResponseEntity<DistrictResponseDTO> callDistrictManagementServiceForCreateDistrict
            (@RequestBody DistrictDTO districtDTO) {
        DistrictResponseDTO districtResponseDTO = districtManagementService.createDistrict(districtDTO);

        return ResponseEntity
                .created(createUriLocationFromCurrentRequest(districtResponseDTO))
                .body(districtResponseDTO);
    }


    private URI createUriLocationFromCurrentRequest(DistrictResponseDTO districtResponseDTO) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(districtResponseDTO.getId())
                .toUri();
    }
}

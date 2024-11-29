package com.kauanProjects.projetoAplicado.controllers;

import com.kauanProjects.projetoAplicado.dtos.CollectDTO;
import com.kauanProjects.projetoAplicado.dtos.CollectResponseDTO;
import com.kauanProjects.projetoAplicado.services.CollectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/collect")
public class CollectRestController {

    private final CollectManagementService collectManagementService;

    @Autowired
    public CollectRestController(CollectManagementService collectManagementService) {
        this.collectManagementService = collectManagementService;
    }


    @PostMapping
    public ResponseEntity<CollectResponseDTO> callCollectManagementServiceCreateCollect
            (@RequestBody CollectDTO collectDTO) {
        CollectResponseDTO collectResponseDTO = collectManagementService.createCollect(collectDTO);

        return ResponseEntity
                .created(createUriLocationFromCurrentRequest(collectResponseDTO))
                .body(collectResponseDTO);
    }

    private URI createUriLocationFromCurrentRequest(CollectResponseDTO collectResponseDTO) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(collectResponseDTO.getId())
                .toUri();
    }

    @PutMapping
    public ResponseEntity<Void> callCollectManagementServiceUpdateScheduleByDistrictAndDayWeek(
            @RequestBody CollectDTO collectDTO) {
        collectManagementService.updateScheduleByDistrictAndDayWeek(collectDTO);

        return ResponseEntity.noContent().build();
    }
}

package com.kauanProjects.projetoAplicado.services;

import com.kauanProjects.projetoAplicado.dtos.CollectResponseDTO;
import com.kauanProjects.projetoAplicado.dtos.DistrictDTO;
import com.kauanProjects.projetoAplicado.dtos.DistrictResponseDTO;
import com.kauanProjects.projetoAplicado.dtos.EcopointResponseDTO;
import com.kauanProjects.projetoAplicado.entities.Collect;
import com.kauanProjects.projetoAplicado.entities.District;
import com.kauanProjects.projetoAplicado.entities.Ecopoint;
import com.kauanProjects.projetoAplicado.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistrictManagementService {

    private final DistrictRepository districtRepository;
    private final CollectManagementService collectManagementService;
    private final EcopointManagementService ecopointManagementService;

    @Autowired
    public DistrictManagementService(DistrictRepository districtRepository,
                                     CollectManagementService collectManagementService,
                                     EcopointManagementService ecopointManagementService) {
        this.districtRepository = districtRepository;
        this.collectManagementService = collectManagementService;
        this.ecopointManagementService = ecopointManagementService;
    }


    public DistrictResponseDTO createDistrict(DistrictDTO districtDTO) {
        District district = new District();
        district.setName(districtDTO.name());

        saveDistrictInDatabase(district);

        return new DistrictResponseDTO(district);
    }


    @Transactional
    private void saveDistrictInDatabase(District district) {
        districtRepository.save(district);
    }


    public List<CollectResponseDTO> fetchCollectionsByDistrict(DistrictDTO districtDTO) {
        List<Collect> collects = collectManagementService
                .fetchCollectsByDistrict(fetchDistrictByName(districtDTO.name()));

        return collects.stream().map(CollectResponseDTO::new).toList();
    }


    @Transactional(readOnly = true)
    private District fetchDistrictByName(String districtName) {
        return districtRepository.findByName(districtName);
    }


    public List<EcopointResponseDTO> fetchEcopointsByDistrict(DistrictDTO districtDTO) {
        List<Ecopoint> ecopoints = ecopointManagementService
                .fetchEcopointsByDistrict(fetchDistrictByName(districtDTO.name()));

        return ecopoints.stream().map(EcopointResponseDTO::new).toList();
    }
}

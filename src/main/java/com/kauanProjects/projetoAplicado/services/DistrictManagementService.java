package com.kauanProjects.projetoAplicado.services;

import com.kauanProjects.projetoAplicado.dtos.DistrictDTO;
import com.kauanProjects.projetoAplicado.dtos.DistrictResponseDTO;
import com.kauanProjects.projetoAplicado.entities.District;
import com.kauanProjects.projetoAplicado.repositories.DistrictRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictManagementService {

    private final DistrictRepository districtRepository;

    @Autowired
    public DistrictManagementService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
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
}

package com.kauanProjects.projetoAplicado.services;

import com.kauanProjects.projetoAplicado.dtos.EcopointDTO;
import com.kauanProjects.projetoAplicado.dtos.EcopointResponseDTO;
import com.kauanProjects.projetoAplicado.entities.District;
import com.kauanProjects.projetoAplicado.entities.Ecopoint;
import com.kauanProjects.projetoAplicado.repositories.EcopointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EcopointManagementService {

    private final EcopointRepository ecopointRepository;

    @Autowired
    public EcopointManagementService(EcopointRepository ecopointRepository) {
        this.ecopointRepository = ecopointRepository;
    }

    public EcopointResponseDTO createEcopoint(EcopointDTO ecopointDTO) {
        Ecopoint ecopoint = new Ecopoint();
        ecopoint.setAddress(ecopointDTO.getAddress());
        ecopoint.setDistrict(ecopointDTO.getDistrict());

        saveEcopointInDatabase(ecopoint);

        return new EcopointResponseDTO(ecopoint);
    }


    @Transactional
    private void saveEcopointInDatabase(Ecopoint ecopoint) {
        ecopointRepository.save(ecopoint);
    }


    @Transactional(readOnly = true)
    public List<Ecopoint> fetchEcopointsByDistrict(District district) {
        return ecopointRepository.findAllByDistrict(district);
    }
}

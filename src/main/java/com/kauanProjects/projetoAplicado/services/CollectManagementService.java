package com.kauanProjects.projetoAplicado.services;

import com.kauanProjects.projetoAplicado.dtos.CollectDTO;
import com.kauanProjects.projetoAplicado.dtos.CollectResponseDTO;
import com.kauanProjects.projetoAplicado.entities.Collect;
import com.kauanProjects.projetoAplicado.entities.District;
import com.kauanProjects.projetoAplicado.repositories.CollectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CollectManagementService {

    private final CollectRepository collectRepository;

    @Autowired
    public CollectManagementService(CollectRepository collectRepository) {
        this.collectRepository = collectRepository;
    }

    public CollectResponseDTO createCollect(CollectDTO collectDTO) {
        Collect collect = new Collect();
        collect.setDistrict(collectDTO.getDistrict());
        collect.setShift(collectDTO.getShift());
        collect.setSchedule(collectDTO.getSchedule());
        collect.setDayOfWeek(collectDTO.getDayOfWeek());

        saveCollectInDatabase(collect);

        return new CollectResponseDTO(collect);
    }


    @Transactional
    private void saveCollectInDatabase(Collect collect) {
        collectRepository.save(collect);
    }


    @Transactional(readOnly = true)
    public List<Collect> fetchCollectsByDistrict(District district) {
        return collectRepository.findAllByDistrict(district);
    }
}

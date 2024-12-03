package com.kauanProjects.projetoAplicado.dtos;

import com.kauanProjects.projetoAplicado.entities.District;
import lombok.Getter;
import java.util.List;

@Getter
public class DistrictResponseDTO {

    private final Long id;
    private final String name;
    private final String ecopoint;
    private final List<CollectResponseDTO> collects;

    public DistrictResponseDTO(District district) {
        id = district.getId();
        name = district.getName();
        ecopoint = (district.getEcopoint() != null) ? district.getEcopoint().getAddress() : null;
        collects = district.getCollects().stream().map(CollectResponseDTO::new).toList();
    }
}

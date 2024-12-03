package com.kauanProjects.projetoAplicado.dtos;

import com.kauanProjects.projetoAplicado.entities.District;
import com.kauanProjects.projetoAplicado.entities.Ecopoint;
import lombok.Getter;
import java.util.List;

@Getter
public class DistrictResponseDTO {

    private final Long id;
    private final String name;
    private final List<String> ecopoint;
    private final List<CollectResponseDTO> collects;

    public DistrictResponseDTO(District district) {
        id = district.getId();
        name = district.getName();
        ecopoint = (district.getEcopoints() != null)
                ? district.getEcopoints().stream().map(Ecopoint::getAddress).toList() : null;
        collects = district.getCollects().stream().map(CollectResponseDTO::new).toList();
    }
}

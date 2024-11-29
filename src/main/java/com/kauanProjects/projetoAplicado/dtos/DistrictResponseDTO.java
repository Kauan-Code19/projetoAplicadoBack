package com.kauanProjects.projetoAplicado.dtos;

import com.kauanProjects.projetoAplicado.entities.Collect;
import com.kauanProjects.projetoAplicado.entities.District;
import com.kauanProjects.projetoAplicado.entities.Ecopoint;
import lombok.Getter;
import java.util.List;

@Getter
public class DistrictResponseDTO {

    private final Long id;
    private final String name;
    private final Ecopoint ecopoint;
    private final List<Collect> collects;

    public DistrictResponseDTO(District district) {
        id = district.getId();
        name = district.getName();
        ecopoint = district.getEcopoint();
        collects = district.getCollects();
    }
}

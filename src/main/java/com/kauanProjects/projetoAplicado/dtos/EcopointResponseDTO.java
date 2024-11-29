package com.kauanProjects.projetoAplicado.dtos;

import com.kauanProjects.projetoAplicado.entities.Ecopoint;
import lombok.Getter;

@Getter
public class EcopointResponseDTO {

    private final Long id;
    private final String address;
    private final String district;

    public EcopointResponseDTO(Ecopoint ecopoint) {
        id = ecopoint.getId();
        address = ecopoint.getAddress();
        district = ecopoint.getDistrict().getName();
    }
}

package com.kauanProjects.projetoAplicado.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kauanProjects.projetoAplicado.entities.District;
import com.kauanProjects.projetoAplicado.entities.DistrictDeserializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class EcopointDTO {

    @NotBlank
    private String address;

    @JsonDeserialize(using = DistrictDeserializer.class)
    @NotNull
    private District district;
}

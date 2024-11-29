package com.kauanProjects.projetoAplicado.dtos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.kauanProjects.projetoAplicado.entities.District;
import com.kauanProjects.projetoAplicado.entities.DistrictDeserializer;
import com.kauanProjects.projetoAplicado.enums.Shift;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
public class CollectDTO {

    @NotNull
    private DayOfWeek dayOfWeek;

    @NotNull
    private Shift shift;

    @NotNull
    private LocalTime schedule;

    @JsonDeserialize(using = DistrictDeserializer.class)
    @NotNull
    private District district;
}

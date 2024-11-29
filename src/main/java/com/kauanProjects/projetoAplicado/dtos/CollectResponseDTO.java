package com.kauanProjects.projetoAplicado.dtos;

import com.kauanProjects.projetoAplicado.entities.Collect;
import com.kauanProjects.projetoAplicado.enums.Shift;
import lombok.Getter;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
public class CollectResponseDTO {

    private final Long id;
    private final Shift shift;
    private final DayOfWeek dayOfWeek;
    private final LocalTime schedule;
    private final String district;

    public CollectResponseDTO(Collect collect) {
        id = collect.getId();
        shift = collect.getShift();
        dayOfWeek = collect.getDayOfWeek();
        schedule = collect.getSchedule();
        district = collect.getDistrict().getName();
    }
}

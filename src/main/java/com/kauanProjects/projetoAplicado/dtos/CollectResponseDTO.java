package com.kauanProjects.projetoAplicado.dtos;

import com.kauanProjects.projetoAplicado.entities.Collect;
import com.kauanProjects.projetoAplicado.enums.Shift;
import lombok.Getter;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

@Getter
public class CollectResponseDTO {

    private final Long id;
    private final Shift shift;
    private final String dayOfWeek;
    private final String schedule;
    private final String district;

    public CollectResponseDTO(Collect collect) {
        id = collect.getId();
        shift = collect.getShift();
        dayOfWeek = formatDayOfWeekInPortuguese(collect.getDayOfWeek());
        schedule = collect.getSchedule().format(formatScheduleForPtBr());
        district = collect.getDistrict().getName();
    }


    private DateTimeFormatter formatScheduleForPtBr() {
        return DateTimeFormatter.ofPattern("HH:mm", Locale.forLanguageTag("pt-br"));
    }


    public String formatDayOfWeekInPortuguese(DayOfWeek dayOfWeek) {
        return dayOfWeek.getDisplayName(TextStyle.FULL, new Locale("pt", "BR")).toUpperCase();
    }

}

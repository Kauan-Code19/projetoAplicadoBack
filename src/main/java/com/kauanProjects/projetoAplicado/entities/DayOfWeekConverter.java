package com.kauanProjects.projetoAplicado.entities;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

@Converter(autoApply = true)
public class DayOfWeekConverter implements AttributeConverter<DayOfWeek, String> {
    @Override
    public String convertToDatabaseColumn(DayOfWeek dayOfWeek) {
        if (dayOfWeek == null) {
            return null;
        }

        return dayOfWeek.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
    }


    @Override
    public DayOfWeek convertToEntityAttribute(String dayOfWeekString) {
        if (dayOfWeekString == null || dayOfWeekString.isEmpty()) {
            return null;
        }

        return switch (dayOfWeekString.toLowerCase(Locale.ROOT)) {
            case "segunda-feira" -> DayOfWeek.MONDAY;
            case "terça-feira" -> DayOfWeek.TUESDAY;
            case "quarta-feira" -> DayOfWeek.WEDNESDAY;
            case "quinta-feira" -> DayOfWeek.THURSDAY;
            case "sexta-feira" -> DayOfWeek.FRIDAY;
            case "sábado" -> DayOfWeek.SATURDAY;
            case "domingo" -> DayOfWeek.SUNDAY;
            default -> throw new IllegalArgumentException("Dia da semana inválido: " + dayOfWeekString);
        };
    }
}

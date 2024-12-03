package com.kauanProjects.projetoAplicado.services;

import com.kauanProjects.projetoAplicado.dtos.CollectDTO;
import com.kauanProjects.projetoAplicado.dtos.CollectResponseDTO;
import com.kauanProjects.projetoAplicado.entities.Collect;
import com.kauanProjects.projetoAplicado.entities.District;
import com.kauanProjects.projetoAplicado.enums.Shift;
import com.kauanProjects.projetoAplicado.repositories.CollectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.DayOfWeek;
import java.time.LocalTime;
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
        collect.setNumberOfSchedules(1L);
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


    public void updateScheduleByDistrictAndDayWeek(CollectDTO collectDTO) {
        Collect collect = fetchCollectByDistrictAndDayOfWeekAndShift(collectDTO.getDistrict(),
                collectDTO.getDayOfWeek(), collectDTO.getShift());

        if (collect == null) {
            createCollect(collectDTO);
            return;
        }

        Long newScheduleInMinutes = convertScheduleToMinutes(collectDTO.getSchedule());
        Long oldScheduleInMinutes = convertScheduleToMinutes(collect.getSchedule());

        Long updatedScheduleInMinutes = calculateNewAverageSchedule(newScheduleInMinutes,
                oldScheduleInMinutes, collect.getNumberOfSchedules());

        collect.setSchedule(convertMinutesToLocalTime(updatedScheduleInMinutes));
        incrementNumberOfSchedules(collect);

        saveCollectInDatabase(collect);
    }


    @Transactional(readOnly = true)
    private Collect fetchCollectByDistrictAndDayOfWeekAndShift(District district, DayOfWeek dayOfWeek, Shift shift) {
        return collectRepository.findByDistrictAndDayOfWeekAndShift(district, dayOfWeek, shift);
    }


    private Long convertScheduleToMinutes(LocalTime schedule) {
        return (long) (schedule.getHour() * 60 + schedule.getMinute());
    }


    private Long calculateNewAverageSchedule(Long newSchedule, Long oldSchedule, Long totalNumberOfTimeSchedule) {
        return oldSchedule + ((newSchedule - oldSchedule) / (totalNumberOfTimeSchedule + 1));
    }


    private LocalTime convertMinutesToLocalTime(Long totalMinutes) {
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;
        return LocalTime.of((int) hours, (int) minutes);
    }


    private void incrementNumberOfSchedules(Collect collect) {
        collect.setNumberOfSchedules(collect.getNumberOfSchedules() + 1);
    }
}

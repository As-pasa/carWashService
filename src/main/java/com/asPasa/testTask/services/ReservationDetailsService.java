package com.asPasa.testTask.services;

import com.asPasa.testTask.models.CarReservation;
import com.asPasa.testTask.models.ReservationDetails;
import com.asPasa.testTask.models.WashType;
import com.asPasa.testTask.repositories.WashTypeRepository;
import com.asPasa.testTask.utils.Scheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationDetailsService {
    private final WashTypeRepository washTypeRepository;
    private final ReservationService bookingService;
    private final Scheduler scheduler;

    public ReservationDetails getReservationDetailsById(Long id) {
        CarReservation carReservation = bookingService.getReservationById(id);
        WashType washType = washTypeRepository.findByNameIgnoreCase(carReservation.getWashType().toString()).getFirst();
        return new ReservationDetails(washType.getName(), carReservation.getUser(), carReservation.getReservationTime(), washType.getDurationMinutes());
    }

    public List<ReservationDetails> reservationsForDay(Date date) {
        Date start = scheduler.getStartTimeForDate(date);
        Date end = scheduler.getEndTimeForDate(date);
        return bookingService.getAll(date).stream().map(
                book -> {

                    WashType washType = washTypeRepository.findByNameIgnoreCase(book.getWashType().getName()).getFirst();
                    return new ReservationDetails(washType.getName(),
                            book.getUser(),
                            book.getReservationTime(),
                            washType.getDurationMinutes());
                }
        ).filter(s->s.getStartDate().after(start)&&s.endDate().before(end)) .collect(Collectors.toList());
    }
}

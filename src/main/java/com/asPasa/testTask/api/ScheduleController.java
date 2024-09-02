package com.asPasa.testTask.api;

import com.asPasa.testTask.exceptions.ApplicationException;
import com.asPasa.testTask.models.CarReservation;
import com.asPasa.testTask.models.User;
import com.asPasa.testTask.models.WashType;
import com.asPasa.testTask.models.dto.*;
import com.asPasa.testTask.repositories.WashTypeRepository;
import com.asPasa.testTask.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
@Slf4j
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final WashTypeService washTypeService;
    private final UserService userService;
    private final ReservationDetailsService reservationDetailsService;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);

    @PostMapping("/book")
    public ResponseEntity<ScheduledResponse> bookTime(@RequestBody BookTimeRequest request) {
        try {
            Date date = formatter.parse(request.getRequestedTime());
            User user = userService.findById(request.getUId());
            WashType washType = washTypeService.findById(request.getWashTypeId());

            scheduleService.bookSlot(user, date, washType);
            String dateFormatted = formatter.format(date);
            return ResponseEntity.ok(new ScheduledResponse(user.getUsername(), dateFormatted, washType.toString()));
        } catch (ParseException e) {
            throw new ApplicationException(e.getMessage());
        }

    }

    @GetMapping("/allBooked")
    public ResponseEntity<AllBookedResponse> getAllBooked(@RequestBody AllForDateRequest date) {
        try {

            Date parsed = formatter.parse(date.getDate());
            List<String> reservations = reservationDetailsService.reservationsForDay(parsed).stream().map(s -> formatter.format(s.getStartDate())).toList();
            return ResponseEntity.ok(new AllBookedResponse(reservations));
        } catch (ParseException e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    @PostMapping("/bookFirstAvailable")
    public ResponseEntity<ScheduledResponse> bookFirstAvailable(@RequestBody BookFirstRequest request) {
        User user = userService.findById(request.getUId());
        WashType washType = washTypeService.findById(request.getWashType());
        Date date = scheduleService.getFirstAvailable(Duration.ofMinutes(washType.getDurationMinutes()));
        log.info(formatter.format(date));
        scheduleService.bookSlot(user,date,washType);
        return ResponseEntity.ok(new ScheduledResponse(user.getUsername(),formatter.format(date), washType.getName()));
    }


    @GetMapping("/forDate")
    public ResponseEntity<ScheduleSlotsResponse> getSlotsForDate(@RequestBody GetScheduleSlotsForDateRequest request) {
        try {
            Date date = formatter.parse(request.getScheduledDate());
            WashType washType = washTypeService.findById(request.getWashTypeId());
            Duration duration = Duration.ofMinutes(washType.getDurationMinutes());
            List<Date> dates = scheduleService.getAvailableForDate(date, duration);
            ScheduleSlotsResponse response = new ScheduleSlotsResponse(dates.stream().map(s->formatter.format(date)).collect(Collectors.toList()), washType.getName());
            return ResponseEntity.ok(response);
        } catch (ParseException e) {
            throw new ApplicationException(e.toString());
        }
    }

    @GetMapping("/firstAvailable")
    public ResponseEntity<ScheduleSlotsResponse> getFirstAvailable(@RequestBody GetFirstRequest request) {

        WashType washType = washTypeService.findById(request.getWashTypeId());
        Duration duration = Duration.ofMinutes(washType.getDurationMinutes());
        ScheduleSlotsResponse response = new ScheduleSlotsResponse(List.of(scheduleService.getFirstAvailable(duration)).stream().map(s->formatter.format(s)).toList(), washType.getName());
        return ResponseEntity.ok(response);
    }

}


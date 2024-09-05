package com.asPasa.testTask.api;


import com.asPasa.testTask.models.BookingSlot;
import com.asPasa.testTask.models.SlotTimeInterval;
import com.asPasa.testTask.models.User;
import com.asPasa.testTask.models.WashType;
import com.asPasa.testTask.models.dto.booking.*;
import com.asPasa.testTask.repositories.BookingSlotRepository;
import com.asPasa.testTask.services.ScheduleService;
import com.asPasa.testTask.services.UserService;
import com.asPasa.testTask.services.WashTypeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookingController {
    private final WashTypeService washTypeService;
    private final ScheduleService scheduleService;
    private final UserService userService;
    private SimpleDateFormat formatter = new SimpleDateFormat("dd.MMM.yyyy HH:mm", Locale.ENGLISH);

    @Operation(summary = "Резервация времени")
    @PostMapping("/book-time")
    public ResponseEntity<BookTimeResponse> book(@RequestBody BookTimeRequest request) throws ParseException {
        WashType washType = washTypeService.findByName(request.getServiceType());
        User user = userService.findByName(request.getUsername());
        BookingSlot slot = new BookingSlot(user, washType, formatter.parse(request.getDate()));
        scheduleService.book(slot);
        BookTimeResponse response = new BookTimeResponse(user.getUsername(), request.getDate(), washType.getName());
        return ResponseEntity.ok(response);
    }
    @Operation(summary = "Получение списка слотов, свободных для резервации в данный день")
    @GetMapping("/all-for-date")
    public ResponseEntity<BookSLotsResponse> findAll(@RequestBody GetFreeSlotsForDayRequest request) throws ParseException {
        WashType washType = washTypeService.findByName(request.getWashType());
        Date date = formatter.parse(request.getDate());
        List<SlotTimeInterval> existingIntervals = scheduleService.getIntervalsForDate(date);
        List<String> dates = scheduleService.findFreeSlots(date, Duration.ofMinutes(washType.getRequiredMinutes()), existingIntervals)
                .stream().map(x->formatter.format(x)).toList();
        return ResponseEntity.ok(new BookSLotsResponse(dates,washType.getName()));
    }
    @Operation(summary = "Получение первого свободного слота")
    @GetMapping("/find-first-slot")
    public ResponseEntity<BookSLotsResponse> findFirst(@RequestBody GetFirstFreeRequest request){
        WashType washType = washTypeService.findByName(request.getWashType());
        Date respDate = scheduleService.findFirstFreeSlot(Duration.ofMinutes(washType.getRequiredMinutes()));
        return ResponseEntity.ok(new BookSLotsResponse(List.of(formatter.format(respDate)),washType.getName()));
    }
    @Operation(summary = "Получение ближайшего по времени слота, который зарезервировал пользователь")
    @GetMapping("/first-for-user")
    public ResponseEntity<UserFirstBookingResponse> fistBookingForUser(@RequestBody UserFirstBookingRequest request){
        User user = userService.findByName(request.getUserName());
        BookingSlot nearest=userService.getNearestBooking(user.getId());
        return ResponseEntity.ok(new UserFirstBookingResponse(formatter.format(nearest.getStartTime()),user.getUsername(),nearest.getWashType().getName()));
    }

}

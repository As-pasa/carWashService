package com.asPasa.testTask.services;

import com.asPasa.testTask.models.BookingSlot;
import com.asPasa.testTask.models.SlotTimeInterval;
import com.asPasa.testTask.repositories.BookingSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingSlotsService {
    private final BookingSlotRepository bookingSlotRepository;


    public List<SlotTimeInterval> getIntervalsIn(SlotTimeInterval intervalIn){
        List<BookingSlot> slots = (List<BookingSlot>) bookingSlotRepository.findAll();
        return slots.stream().map(BookingSlot::getInterval).filter(x->x.intersect(intervalIn)).collect(Collectors.toList());
    }
    public BookingSlot persistBooking(BookingSlot bookingSlot){

        return bookingSlotRepository.save(bookingSlot);
    }


}

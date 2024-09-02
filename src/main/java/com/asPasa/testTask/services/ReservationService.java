package com.asPasa.testTask.services;

import com.asPasa.testTask.exceptions.ApplicationException;
import com.asPasa.testTask.models.CarReservation;
import com.asPasa.testTask.repositories.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final BookingRepository bookingRepository;


    public CarReservation getReservationById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new ApplicationException("No suchBooking"));
    }

    public List<CarReservation> getAll(Date date) {

        return ((List<CarReservation>) bookingRepository.findAll());

    }



    public CarReservation save(CarReservation reservation) {
        return bookingRepository.save(reservation);
    }
}

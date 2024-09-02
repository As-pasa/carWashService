package com.asPasa.testTask.repositories;

import com.asPasa.testTask.models.CarReservation;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends CrudRepository<CarReservation, Long> {

}

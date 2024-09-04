package com.asPasa.testTask.repositories;

import com.asPasa.testTask.models.BookingSlot;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingSlotRepository extends CrudRepository<BookingSlot,Long> {

}

package com.awbd.booking.repositories;

import com.awbd.booking.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    Booking findByCustomerNameAndDestination(String customerName, String destination);

}

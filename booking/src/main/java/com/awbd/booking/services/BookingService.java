package com.awbd.booking.services;

import com.awbd.booking.model.Booking;

import java.util.List;

public interface BookingService {
    Booking findById(Long id);
    List<Booking> findAll();
    Booking save(Booking booking);
    void delete(Long id);
}

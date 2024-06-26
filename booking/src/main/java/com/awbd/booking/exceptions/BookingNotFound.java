package com.awbd.booking.exceptions;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookingNotFound extends RuntimeException {
    public BookingNotFound(String message) {
        super(message);
    }
}

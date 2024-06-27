package com.awbd.booking.controller;

import com.awbd.booking.model.Booking;
import com.awbd.booking.services.BookingService;
import com.awbd.booking.services.NotificationServiceProxy;
import com.awbd.notification.model.Notification;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private NotificationServiceProxy notificationService;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.findAll();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.findById(id);
    }

    @PostMapping
    @CircuitBreaker(name = "notification", fallbackMethod = "notificationFallback")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking createdBooking = bookingService.save(booking);

        // send notification after booking is created
        com.awbd.booking.model.Notification notification = new com.awbd.booking.model.Notification("New booking created: " + createdBooking.getId());
        ResponseEntity<String> response = notificationService.sendNotification(notification);

        System.out.println("Notification service response: " + response.getBody());

        return ResponseEntity.ok().body(createdBooking);
    }

    @GetMapping("/notifications")
    public List<com.awbd.booking.model.Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
        updatedBooking.setId(id);
        return bookingService.save(updatedBooking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.delete(id);
    }

    public ResponseEntity<Booking> notificationFallback(Booking booking, Throwable t) {
        System.out.println("Notification service is down, fallback method called");
        return ResponseEntity.ok().body(booking);
    }
}

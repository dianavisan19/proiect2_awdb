package com.awbd.booking.controller;

import com.awbd.booking.model.Booking;
import com.awbd.booking.services.BookingService;
import com.awbd.booking.services.NotificationServiceProxy;
import com.awbd.notification.model.Notification;
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
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking createdBooking = bookingService.save(booking);

        // Send notification after booking is created
        Notification notification = new Notification("New booking created: " + createdBooking.getId());
        ResponseEntity<String> response = notificationService.sendNotification(notification);

        System.out.println("Notification service response: " + response.getBody());

        return ResponseEntity.ok().body(createdBooking);
    }

    @GetMapping("/notifications")
    public List<Notification> getAllNotifications() {
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
}

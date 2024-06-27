package com.awbd.booking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "booking")
public class Booking extends RepresentationModel<Booking> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message="destination should not be empty")
    @Column(name = "destination")
    private String destination;

    @Column(name = "date")
    private String date;

    @NotBlank(message="customer_name is required")
    @Column(name = "customer_name")
    private String customerName;

}

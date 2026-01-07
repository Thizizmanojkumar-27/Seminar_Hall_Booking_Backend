package com.seminar.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking_requests")
public class BookingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String slot; // Morning / Midday / Evening

    @Column(nullable = false)
    private String purpose;

    @Column(nullable = false)
    private String uucms; // User ID who requested

    @Column(nullable = false)
    private String branch;

    @Column(nullable = false)
    private String status; // PENDING / APPROVED / REJECTED
}

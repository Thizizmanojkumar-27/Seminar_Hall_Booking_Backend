package com.seminar.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {
    private String uucms;
    private LocalDate date;
    private String slot;
    private String purpose;
    private String branch;
}

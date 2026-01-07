package com.seminar.booking.repository;

import com.seminar.booking.entity.BookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRequestRepository extends JpaRepository<BookingRequest, Long> {
    List<BookingRequest> findByUucms(String uucms);
    List<BookingRequest> findByStatus(String status);
}

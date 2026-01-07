package com.seminar.booking.service;

import com.seminar.booking.entity.BookingRequest;
import com.seminar.booking.repository.BookingRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRequestRepository bookingRequestRepository;

    public List<BookingRequest> getAllPendingRequests() {
        return bookingRequestRepository.findByStatus("PENDING");
    }

    public String validateRequest(Long requestId, String status) {
        Optional<BookingRequest> requestOpt = bookingRequestRepository.findById(requestId);
        if (requestOpt.isPresent()) {
            BookingRequest request = requestOpt.get();
            if ("APPROVED".equalsIgnoreCase(status) || "REJECTED".equalsIgnoreCase(status)) {
                request.setStatus(status.toUpperCase());
                bookingRequestRepository.save(request);
                return "Request " + status.toLowerCase();
            } else {
                return "Invalid status";
            }
        }
        return "Request not found";
    }
}

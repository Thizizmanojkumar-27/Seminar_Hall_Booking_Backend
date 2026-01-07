package com.seminar.booking.service;

import com.seminar.booking.dto.BookingDto;
import com.seminar.booking.entity.BookingRequest;
import com.seminar.booking.entity.User;
import com.seminar.booking.repository.BookingRequestRepository;
import com.seminar.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRequestRepository bookingRequestRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    public String changePassword(String uucms, String oldPassword, String newPassword) {
        Optional<User> userOpt = userRepository.findByUucms(uucms);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(oldPassword, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
                return "Password changed successfully";
            } else {
                return "Invalid old password";
            }
        }
        return "User not found";
    }

    public String bookSlot(BookingDto bookingDto) {
        BookingRequest request = new BookingRequest();
        request.setUucms(bookingDto.getUucms());
        request.setDate(bookingDto.getDate());
        request.setSlot(bookingDto.getSlot());
        request.setPurpose(bookingDto.getPurpose());
        request.setBranch(bookingDto.getBranch());
        request.setStatus("PENDING");
        
        bookingRequestRepository.save(request);
        return "Booking request submitted successfully";
    }

    public List<BookingRequest> getUserRequests(String uucms) {
        return bookingRequestRepository.findByUucms(uucms);
    }
}

package com.seminar.booking.controller;

import com.seminar.booking.dto.AuthRequest;
import com.seminar.booking.dto.BookingDto;
import com.seminar.booking.entity.BookingRequest;
import com.seminar.booking.entity.User;
import com.seminar.booking.security.JwtUtil;
import com.seminar.booking.service.CustomUserDetailsService;
import com.seminar.booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid credentials");
        }
        return jwtUtil.generateToken(authRequest.getUsername());
    }

    @PostMapping("/password")
    public String changePassword(@RequestBody Map<String, String> request) {
        return userService.changePassword(request.get("uucms"), request.get("oldPassword"), request.get("newPassword"));
    }

    @PostMapping("/book")
    public String bookSlot(@RequestBody BookingDto bookingDto) {
        return userService.bookSlot(bookingDto);
    }

    @GetMapping("/request/{uucms}")
    public List<BookingRequest> getUserRequests(@PathVariable String uucms) {
        return userService.getUserRequests(uucms);
    }
}

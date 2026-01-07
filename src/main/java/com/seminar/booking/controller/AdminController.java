package com.seminar.booking.controller;

import com.seminar.booking.dto.AuthRequest;
import com.seminar.booking.entity.Admin;
import com.seminar.booking.entity.BookingRequest;
import com.seminar.booking.security.JwtUtil;
import com.seminar.booking.service.AdminService;
import com.seminar.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/newadmin")
    public String registerAdmin(@RequestBody Admin admin) {
        return adminService.registerAdmin(admin);
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
        return adminService.changePassword(request.get("username"), request.get("oldPassword"), request.get("newPassword"));
    }

    @GetMapping("/request")
    public List<BookingRequest> getAllPendingRequests() {
        return bookingService.getAllPendingRequests();
    }

    @PostMapping("/validate")
    public String validateRequest(@RequestBody Map<String, Object> request) {
        Long requestId = Long.valueOf(request.get("requestId").toString());
        String status = (String) request.get("status");
        return bookingService.validateRequest(requestId, status);
    }
}

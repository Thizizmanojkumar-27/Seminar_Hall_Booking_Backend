package com.seminar.booking.service;

import com.seminar.booking.entity.Admin;
import com.seminar.booking.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);
        return "Admin registered successfully";
    }

    public String changePassword(String username, String oldPassword, String newPassword) {
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            if (passwordEncoder.matches(oldPassword, admin.getPassword())) {
                admin.setPassword(passwordEncoder.encode(newPassword));
                adminRepository.save(admin);
                return "Password changed successfully";
            } else {
                return "Invalid old password";
            }
        }
        return "Admin not found";
    }
}

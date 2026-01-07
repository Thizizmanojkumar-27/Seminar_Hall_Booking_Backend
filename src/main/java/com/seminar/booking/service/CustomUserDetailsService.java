package com.seminar.booking.service;

import com.seminar.booking.entity.Admin;
import com.seminar.booking.entity.User;
import com.seminar.booking.repository.AdminRepository;
import com.seminar.booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try finding in User table first
        Optional<User> user = userRepository.findByUucms(username);
        if (user.isPresent()) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.get().getUucms())
                    .password(user.get().getPassword())
                    .roles("USER")
                    .build();
        }

        // Try finding in Admin table
        Optional<Admin> admin = adminRepository.findByUsername(username);
        if (admin.isPresent()) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(admin.get().getUsername())
                    .password(admin.get().getPassword())
                    .roles("ADMIN")
                    .build();
        }

        throw new UsernameNotFoundException("User/Admin not found with username: " + username);
    }
}

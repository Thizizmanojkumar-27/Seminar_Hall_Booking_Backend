package com.seminar.booking.repository;

import com.seminar.booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUucms(String uucms);
}

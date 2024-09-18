package com.example.identity_service.repository;

import com.example.identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> { //entity và kiểu id
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username); //auto find

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
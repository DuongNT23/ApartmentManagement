package com.example.identity_service.repository;

import com.example.identity_service.dto.reponse.UserResponse;
import com.example.identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> { //entity và kiểu id
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username); //auto find

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    @Query(value = "SELECT u.* FROM `user` u WHERE " +
            "(:username IS NULL OR u.username LIKE CONCAT('%',:username,'%')) AND " +
            "(:email IS NULL OR u.email LIKE CONCAT('%',:email,'%')) AND " +
            "(:status IS NULL OR u.status LIKE CONCAT('%',:status,'%')) AND " +
            "(:role_id IS NULL OR u.role_id = :role_id)", nativeQuery = true)
    List<User> findUsers(@Param("username") String username,
                         @Param("email") String email,
                         @Param("status") String status,
                         @Param("role_id") int role_id);

}
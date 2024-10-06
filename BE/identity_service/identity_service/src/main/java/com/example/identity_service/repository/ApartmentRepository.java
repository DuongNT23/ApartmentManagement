package com.example.identity_service.repository;

import com.example.identity_service.entity.Apartment;
import com.example.identity_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, String> { //entity và kiểu id
    boolean existsByUnitNumber(String unitNumber);

    @Query(value = "SELECT a.* FROM `apartments` a WHERE " +
            "(:unit_number IS NULL OR a.unit_number LIKE CONCAT('%',:unit_number,'%')) AND " +
            "(:floor IS NULL OR a.floor LIKE CONCAT('%',:floor,'%')) AND " +
            "(:area IS NULL OR a.area LIKE CONCAT('%',:area,'%')) AND " +
            "(:status IS NULL OR a.status LIKE CONCAT('%',:status,'%')) AND " +
            "(:num_rooms IS NULL OR a.num_rooms LIKE CONCAT('%',:num_rooms,'%'))", nativeQuery = true)
    List<Apartment> findApartments(@Param("unit_number") String unit_number,
                         @Param("floor") String floor,
                         @Param("area") String area,
                         @Param("status") String status,
                         @Param("num_rooms") String num_rooms);

    Apartment findByunitNumber(String unitNumber);

    @Query("SELECT a.unitNumber FROM Apartment a")
    List<String> findAllUnitNumbers();
}
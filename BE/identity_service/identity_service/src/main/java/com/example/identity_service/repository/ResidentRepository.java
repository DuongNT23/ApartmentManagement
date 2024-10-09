package com.example.identity_service.repository;

import com.example.identity_service.entity.Billing;
import com.example.identity_service.entity.Resident;
import com.example.identity_service.enums.BillType;
import com.example.identity_service.enums.PaymentStatus;
import com.example.identity_service.enums.ResidentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, String> {
    @Query("SELECT r FROM Resident r JOIN Contract c ON r.residentId = c.resident.residentId " +
            "AND (:name IS NULL OR LOWER(r.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:status IS NULL OR r.status = :status)")
    List<Resident> searchResidents(@Param("name") String name,
                                   @Param("status") ResidentStatus status);

    @Modifying
    @Query(value = "UPDATE residents SET status = :newStatus WHERE resident_id IN (SELECT resident_id FROM contracts WHERE apartment_id = :apartmentId)", nativeQuery = true)
    int updateResidentStatusByApartmentId(@Param("apartmentId") String apartmentId, @Param("newStatus") String newStatus);

}
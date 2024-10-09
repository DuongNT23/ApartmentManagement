package com.example.identity_service.repository;

import com.example.identity_service.entity.Apartment;
import com.example.identity_service.entity.Billing;
import com.example.identity_service.enums.BillType;
import com.example.identity_service.enums.PaymentStatus;
import com.example.identity_service.enums.ResidentStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Billing, String> { //entity và kiểu id
    List<Billing> findByApartmentApartmentIdAndPaymentStatus(String apartmentId, PaymentStatus paymentStatus);

    @Query("SELECT b FROM Billing b WHERE b.apartment.unitNumber = :apartmentId " +
            "AND b.billType = :billType " +
            "AND FUNCTION('MONTH', b.dueDate) = :month " +
            "AND FUNCTION('YEAR', b.dueDate) = :year")
    List<Billing> findBill(
            @Param("apartmentId") String apartmentId,
            @Param("billType") BillType billType,
            @Param("month") int month,
            @Param("year") int year);

    @Query("SELECT b FROM Billing b WHERE " +
            "(:unitNumber IS NULL OR b.apartment.unitNumber LIKE %:unitNumber%) " +
            "AND (:billType IS NULL OR b.billType = :billType) " +
            "AND (:dueDate IS NULL OR b.dueDate = :dueDate) " +
            "AND (:paymentStatus IS NULL OR b.paymentStatus = :paymentStatus) " +
            "AND b.isDelete = false")
    List<Billing> searchBills(@Param("unitNumber") String unitNumber,
                              @Param("billType") BillType billType,
                              @Param("dueDate") LocalDate dueDate,
                              @Param("paymentStatus") PaymentStatus paymentStatus);

    @Query("SELECT COUNT(b) FROM Billing b WHERE b.apartment.apartmentId = :apartmentId AND b.paymentStatus = :status AND b.isDelete = false")
    Long countBillsByApartmentId(String apartmentId, @Param("status") PaymentStatus status);

    @Modifying
    @Query("UPDATE Billing b SET b.paymentStatus = :newStatus WHERE b.apartment.apartmentId = :apartmentId AND b.paymentStatus IN :oldStatuses")
    int updateBillingStatusByApartmentId(
            @Param("apartmentId") String apartmentId,
            @Param("newStatus") PaymentStatus newStatus,
            @Param("oldStatuses") List<PaymentStatus> oldStatuses);

    @Modifying
    @Query(value = "UPDATE billing SET payment_status = :newStatus WHERE apartment_id = :apartmentId AND payment_status = :oldStatus", nativeQuery = true)
    int updateBillingStatusByApartmentIdAndOldStatus(
            @Param("apartmentId") String apartmentId,
            @Param("newStatus") String newStatus,
            @Param("oldStatus") String oldStatus);
}
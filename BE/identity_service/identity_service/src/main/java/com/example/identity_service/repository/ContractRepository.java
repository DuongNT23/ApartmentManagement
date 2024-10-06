package com.example.identity_service.repository;

import com.example.identity_service.dto.reponse.ResidentResponse;
import com.example.identity_service.entity.Contract;
import com.example.identity_service.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, String> {
    @Query("SELECT c.resident FROM Contract c WHERE c.apartment.apartmentId = :apartmentId")
    List<Resident> findByApartmentApartmentId(String apartmentId);

    Contract findByResidentResidentId(String residentId);

    @Query("SELECT c.apartment.apartmentId FROM Contract c WHERE c.user.id = :userId")
    String findApartmentByUser(String userId);
}
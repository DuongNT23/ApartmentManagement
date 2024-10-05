package com.example.identity_service.repository;

import com.example.identity_service.entity.Billing;
import com.example.identity_service.entity.Resident;
import com.example.identity_service.enums.BillType;
import com.example.identity_service.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, String> { //entity và kiểu id

}
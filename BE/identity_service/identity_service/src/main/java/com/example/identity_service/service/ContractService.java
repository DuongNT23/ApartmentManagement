package com.example.identity_service.service;

import com.example.identity_service.dto.reponse.BillResponse;
import com.example.identity_service.dto.reponse.ResidentResponse;
import com.example.identity_service.dto.request.BillCreationRequest;
import com.example.identity_service.dto.request.BillUpdateRequest;
import com.example.identity_service.dto.request.ResidentCreationRequest;
import com.example.identity_service.dto.request.ResidentUpdateRequest;
import com.example.identity_service.entity.Apartment;
import com.example.identity_service.entity.Billing;
import com.example.identity_service.entity.Resident;
import com.example.identity_service.enums.BillType;
import com.example.identity_service.enums.PaymentStatus;
import com.example.identity_service.enums.ResidentStatus;
import com.example.identity_service.exception.AppException;
import com.example.identity_service.exception.ErrorCode;
import com.example.identity_service.mapper.BillMapper;
import com.example.identity_service.mapper.ResidentMapper;
import com.example.identity_service.repository.ApartmentRepository;
import com.example.identity_service.repository.BillRepository;
import com.example.identity_service.repository.ResidentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ResidentService {
    ResidentRepository residentRepository;
    ResidentMapper residentMapper;
    ApartmentRepository apartmentRepository;

    public ResidentResponse createRequest(ResidentCreationRequest request) {
        log.info("Service: Create resident");

        Resident resident = residentMapper.toResident(request);

        return residentMapper.toResidentResponse(residentRepository.save(resident));
    }

    public List<ResidentResponse> getAllResidents(Pageable pageable) {
        log.info("Service: get all residents");
        return residentRepository.findAll(pageable).stream().map(residentMapper::toResidentResponse).toList();
    }

    public Long getTotalResident() {
        return residentRepository.count();
    }

    public ResidentResponse updateResident(String residentId, ResidentUpdateRequest request) {
        Resident resident = residentRepository.findById(residentId).orElseThrow(() -> new AppException(ErrorCode.RESIDENT_NOT_EXISTED));

        residentMapper.updateResident( resident ,request);
        return residentMapper.toResidentResponse(residentRepository.save(resident));
    }

    public String deleteResident(String residentId) {
        Resident resident = residentRepository.findById(residentId).orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_EXISTED));
        resident.setStatus(ResidentStatus.former);
        residentRepository.save(resident);
        return "Detele successfully";
    }
}


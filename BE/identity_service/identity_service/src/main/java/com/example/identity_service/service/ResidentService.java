package com.example.identity_service.service;

import com.example.identity_service.dto.reponse.BillResponse;
import com.example.identity_service.dto.reponse.ContractResponse;
import com.example.identity_service.dto.reponse.ResidentResponse;
import com.example.identity_service.dto.request.*;
import com.example.identity_service.entity.*;
import com.example.identity_service.enums.BillType;
import com.example.identity_service.enums.ContractStatus;
import com.example.identity_service.enums.PaymentStatus;
import com.example.identity_service.enums.ResidentStatus;
import com.example.identity_service.exception.AppException;
import com.example.identity_service.exception.ErrorCode;
import com.example.identity_service.mapper.BillMapper;
import com.example.identity_service.mapper.ResidentMapper;
import com.example.identity_service.repository.*;
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
    UserRepository userRepository;
    ResidentRepository residentRepository;
    ResidentMapper residentMapper;
    ApartmentRepository apartmentRepository;
    ContractRepository contractRepository;

    public ResidentResponse createRequest(ResidentCreationRequest request) {
        log.info("Service: Create resident");

        Resident resident = residentMapper.toResident(request);
        resident = residentRepository.save(resident);

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Apartment apartment = apartmentRepository.findByunitNumber(request.getApartmentUnitNum());
        Contract contract = new Contract( "",user, apartment, resident, LocalDate.now(), null, ContractStatus.active, false);
        contractRepository.save(contract);

        return residentMapper.toResidentResponse(resident);
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
        residentMapper.updateResident(resident ,request);

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Apartment apartment = apartmentRepository.findByunitNumber(request.getApartmentUnitNum());
        Contract contract = contractRepository.findByResidentResidentId(residentId);
        contract.setApartment(apartment);
        contract.setUser(user);
        contractRepository.save(contract);

        return residentMapper.toResidentResponse(residentRepository.save(resident));
    }

    public String deleteResident(String residentId) {
        Resident resident = residentRepository.findById(residentId).orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_EXISTED));
        resident.setStatus(ResidentStatus.former);
        residentRepository.save(resident);
        return "Detele successfully";
    }

    public ResidentResponse getResidentById(String residentId) {
        Resident resident = residentRepository.findById(residentId).orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_EXISTED));
        return residentMapper.toResidentResponse(resident);
    }

    public List<ResidentResponse> getResidentByApartment(String apartmentId) {
        return contractRepository.findByApartmentApartmentId(apartmentId).stream().map(residentMapper::toResidentResponse).toList();
    }

    public List<ResidentResponse> searchResidents(String name, ResidentStatus status) {
        return residentRepository.searchResidents(name, status).stream().map(residentMapper::toResidentResponse).toList();
    }
}


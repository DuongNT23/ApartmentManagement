package com.example.identity_service.service;

import com.example.identity_service.dto.reponse.BillResponse;
import com.example.identity_service.dto.reponse.ContractResponse;
import com.example.identity_service.dto.reponse.ResidentResponse;
import com.example.identity_service.dto.request.ContractCreationRequest;
import com.example.identity_service.dto.request.ContractUpdateRequest;
import com.example.identity_service.dto.request.ResidentCreationRequest;
import com.example.identity_service.dto.request.ResidentUpdateRequest;
import com.example.identity_service.entity.Apartment;
import com.example.identity_service.entity.Contract;
import com.example.identity_service.entity.Resident;
import com.example.identity_service.entity.User;
import com.example.identity_service.enums.ContractStatus;
import com.example.identity_service.enums.PaymentStatus;
import com.example.identity_service.enums.ResidentStatus;
import com.example.identity_service.exception.AppException;
import com.example.identity_service.exception.ErrorCode;
import com.example.identity_service.mapper.ContractMapper;
import com.example.identity_service.mapper.ResidentMapper;
import com.example.identity_service.repository.ApartmentRepository;
import com.example.identity_service.repository.ContractRepository;
import com.example.identity_service.repository.ResidentRepository;
import com.example.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ContractService {
    UserRepository userRepository;
    ContractRepository contractRepository;
    ContractMapper contractMapper;
    ApartmentRepository apartmentRepository;
    ResidentRepository residentRepository;

    public ContractResponse createRequest(ContractCreationRequest request) {
        log.info("Service: Create resident");

        Contract contract = contractMapper.toContract(request);

        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        contract.setUser(user);

        Apartment apartment = apartmentRepository.findById(request.getApartmentId()).orElseThrow(() -> new AppException(ErrorCode.APARTMENT_NOT_EXISTED));
        contract.setApartment(apartment);

        Resident resident = residentRepository.findById(request.getResidentId()).orElseThrow(() -> new AppException(ErrorCode.RESIDENT_NOT_EXISTED));
        contract.setResident(resident);

        return contractMapper.toContractResponse(contractRepository.save(contract));
    }

    public List<ContractResponse> getAllContracts(Pageable pageable) {
        log.info("Service: get all contracts");
        return contractRepository.findAll(pageable).stream().map(contractMapper::toContractResponse).toList();
    }

    public Long getTotalContract() {
        return contractRepository.count();
    }

    public ContractResponse updateContract(String contractId, ContractUpdateRequest request) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new AppException(ErrorCode.CONTRACT_NOT_EXISTED));

        contractMapper.updateContract( contract ,request);

        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        contract.setUser(user);

        Apartment apartment = apartmentRepository.findById(request.getApartmentId()).orElseThrow(() -> new AppException(ErrorCode.APARTMENT_NOT_EXISTED));
        contract.setApartment(apartment);

        Resident resident = residentRepository.findById(request.getResidentId()).orElseThrow(() -> new AppException(ErrorCode.RESIDENT_NOT_EXISTED));
        contract.setResident(resident);

        return contractMapper.toContractResponse(contractRepository.save(contract));
    }

    public String deleteContract(String contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new AppException(ErrorCode.CONTRACT_NOT_EXISTED));
        contract.setContractStatus(ContractStatus.terminated);
        contractRepository.save(contract);
        return "Detele successfully";
    }

    public ContractResponse getContractById(String contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow(() -> new AppException(ErrorCode.CONTRACT_NOT_EXISTED));
        return contractMapper.toContractResponse(contract);
    }

//    public List<ContractResponse> getContractByApartment(String apartmentId) {
//        return contractRepository.findByApartmentApartmentId(apartmentId).stream().map(contractMapper::toContractResponse).toList();
//    }

    public ContractResponse getContractByResident(String residentId) {
        Contract contract = contractRepository.findByResidentResidentId(residentId);
        return contractMapper.toContractResponse(contract);
    }

    public String getApartmentByUser(String userId) {
        return contractRepository.findApartmentByUser(userId);
    }
}


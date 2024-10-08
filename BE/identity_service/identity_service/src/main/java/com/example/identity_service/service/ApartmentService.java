package com.example.identity_service.service;

import com.example.identity_service.dto.reponse.ApartmentResponse;
import com.example.identity_service.dto.request.ApartmentCreationRequest;
import com.example.identity_service.dto.request.ApartmentUpdateRequest;
import com.example.identity_service.entity.Apartment;
import com.example.identity_service.exception.AppException;
import com.example.identity_service.exception.ErrorCode;
import com.example.identity_service.mapper.ApartmentMapper;
import com.example.identity_service.repository.ApartmentRepository;
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
public class ApartmentService {
    ApartmentRepository apartmentRepository;
    ApartmentMapper apartmentMapper;

    public ApartmentResponse createRequest(ApartmentCreationRequest request){
        log.info("Service: Create apartment");

            if(apartmentRepository.existsByUnitNumber(request.getUnitNumber()) ){
                throw new AppException(ErrorCode.UNIT_NUMBER_EXISTED);
            }

            Apartment apartment = apartmentMapper.toApartment(request);
            return apartmentMapper.toApartmentResponse(apartmentRepository.save(apartment));
    }

    public List<ApartmentResponse> getApartment(Pageable pageable){
        log.info("In method get all apartments");
        return apartmentRepository.findAll(pageable).stream().map(apartmentMapper::toApartmentResponse).toList();
    }

    public Long getTotalApartment() {
        return apartmentRepository.count();
    }

    public ApartmentResponse updateApartment(String aparmentId, ApartmentUpdateRequest request){
        if(request.getStatus().toString().equals("vacant") || request.getStatus().toString().equals("under_maintenance")){
            //update resident thành temporary_vắng mặt
        }

        Apartment apartment = apartmentRepository.findById(aparmentId).orElseThrow(() -> new AppException(ErrorCode.APARTMENT_NOT_EXISTED));

        apartmentMapper.updateApartment( apartment ,request);
        return apartmentMapper.toApartmentResponse(apartmentRepository.save(apartment));
    }

    public ApartmentResponse getApartmentById(String aparmentId){
        log.info("Service: Get apartment by Id");
        Apartment apartment = apartmentRepository.findById(aparmentId).orElseThrow(() -> new AppException(ErrorCode.APARTMENT_NOT_EXISTED));
        return apartmentMapper.toApartmentResponse(apartment);
    }

    public List<ApartmentResponse> searchApartments(String unit_number, String floor, String area, String status, String num_rooms) {
        return apartmentRepository.findApartments(unit_number, floor, area, status, num_rooms).stream().map(apartmentMapper::toApartmentResponse).toList();
    }

    public List<String> getAllUnitNumber(){
        return apartmentRepository.findAllUnitNumbers();
    }
}


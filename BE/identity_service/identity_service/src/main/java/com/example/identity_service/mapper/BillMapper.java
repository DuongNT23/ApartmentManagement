package com.example.identity_service.mapper;

import com.example.identity_service.dto.reponse.ApartmentResponse;
import com.example.identity_service.dto.request.ApartmentCreationRequest;
import com.example.identity_service.dto.request.ApartmentUpdateRequest;
import com.example.identity_service.entity.Apartment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ApartmentMapper {
    @Mapping(target = "apartmentId", ignore = true)
    Apartment toApartment(ApartmentCreationRequest request);
    ApartmentResponse toApartmentResponse (Apartment apartment);
    void updateApartment(@MappingTarget Apartment apartment, ApartmentUpdateRequest request);
}

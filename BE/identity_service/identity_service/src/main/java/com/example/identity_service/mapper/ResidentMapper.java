package com.example.identity_service.mapper;

import com.example.identity_service.dto.reponse.ApartmentResponse;
import com.example.identity_service.dto.reponse.BillResponse;
import com.example.identity_service.dto.request.ApartmentCreationRequest;
import com.example.identity_service.dto.request.ApartmentUpdateRequest;
import com.example.identity_service.dto.request.BillCreationRequest;
import com.example.identity_service.dto.request.BillUpdateRequest;
import com.example.identity_service.entity.Apartment;
import com.example.identity_service.entity.Billing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BillMapper {
    @Mapping(target = "apartment", ignore = true)
    Billing toBill(BillCreationRequest request);
    BillResponse toBillResponse (Billing billing);
    void updateBill(@MappingTarget Billing billing, BillUpdateRequest request);
}

package com.example.identity_service.mapper;

import com.example.identity_service.dto.reponse.BillResponse;
import com.example.identity_service.dto.reponse.ResidentResponse;
import com.example.identity_service.dto.request.BillCreationRequest;
import com.example.identity_service.dto.request.BillUpdateRequest;
import com.example.identity_service.dto.request.ResidentCreationRequest;
import com.example.identity_service.dto.request.ResidentUpdateRequest;
import com.example.identity_service.entity.Billing;
import com.example.identity_service.entity.Resident;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ResidentMapper {
//    @Mapping(target = "apartment", ignore = true)
    Resident toResident(ResidentCreationRequest request);
    ResidentResponse toResidentResponse (Resident resident);
    void updateResident(@MappingTarget Resident resident, ResidentUpdateRequest request);
}

package com.example.identity_service.mapper;

import com.example.identity_service.dto.reponse.ContractResponse;
import com.example.identity_service.dto.reponse.ResidentResponse;
import com.example.identity_service.dto.request.ContractCreationRequest;
import com.example.identity_service.dto.request.ContractUpdateRequest;
import com.example.identity_service.dto.request.ResidentCreationRequest;
import com.example.identity_service.dto.request.ResidentUpdateRequest;
import com.example.identity_service.entity.Contract;
import com.example.identity_service.entity.Resident;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContractMapper {
    @Mapping(target = "contractId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "apartment", ignore = true)
    @Mapping(target = "resident", ignore = true)
    Contract toContract(ContractCreationRequest request);
    ContractResponse toContractResponse (Contract contract);
    @Mapping(target = "contractId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "apartment", ignore = true)
    @Mapping(target = "resident", ignore = true)
    void updateContract(@MappingTarget Contract contract, ContractUpdateRequest request);
}

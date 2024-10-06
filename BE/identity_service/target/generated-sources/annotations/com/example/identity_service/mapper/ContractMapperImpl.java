package com.example.identity_service.mapper;

import com.example.identity_service.dto.reponse.ContractResponse;
import com.example.identity_service.dto.request.ContractCreationRequest;
import com.example.identity_service.dto.request.ContractUpdateRequest;
import com.example.identity_service.entity.Contract;
import com.example.identity_service.enums.ContractStatus;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class ContractMapperImpl implements ContractMapper {

    @Override
    public Contract toContract(ContractCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Contract.ContractBuilder contract = Contract.builder();

        contract.startDate( request.getStartDate() );
        contract.endDate( request.getEndDate() );
        contract.contractStatus( request.getContractStatus() );
        contract.isRepresentative( request.getIsRepresentative() );

        return contract.build();
    }

    @Override
    public ContractResponse toContractResponse(Contract contract) {
        if ( contract == null ) {
            return null;
        }

        ContractResponse.ContractResponseBuilder contractResponse = ContractResponse.builder();

        contractResponse.contractId( contract.getContractId() );
        contractResponse.user( contract.getUser() );
        contractResponse.apartment( contract.getApartment() );
        contractResponse.resident( contract.getResident() );
        contractResponse.startDate( contract.getStartDate() );
        contractResponse.endDate( contract.getEndDate() );
        contractResponse.contractStatus( contract.getContractStatus() );
        contractResponse.isRepresentative( contract.getIsRepresentative() );

        return contractResponse.build();
    }

    @Override
    public void updateContract(Contract contract, ContractUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        contract.setStartDate( request.getStartDate() );
        contract.setEndDate( request.getEndDate() );
        if ( request.getContractStatus() != null ) {
            contract.setContractStatus( Enum.valueOf( ContractStatus.class, request.getContractStatus() ) );
        }
        else {
            contract.setContractStatus( null );
        }
        contract.setIsRepresentative( request.getIsRepresentative() );
    }
}

package com.example.identity_service.mapper;

import com.example.identity_service.dto.reponse.ResidentResponse;
import com.example.identity_service.dto.request.ResidentCreationRequest;
import com.example.identity_service.dto.request.ResidentUpdateRequest;
import com.example.identity_service.entity.Resident;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class ResidentMapperImpl implements ResidentMapper {

    @Override
    public Resident toResident(ResidentCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Resident.ResidentBuilder resident = Resident.builder();

        resident.name( request.getName() );
        resident.email( request.getEmail() );
        resident.phone( request.getPhone() );
        resident.idCard( request.getIdCard() );
        resident.dob( request.getDob() );
        resident.gender( request.getGender() );
        resident.status( request.getStatus() );

        return resident.build();
    }

    @Override
    public ResidentResponse toResidentResponse(Resident resident) {
        if ( resident == null ) {
            return null;
        }

        ResidentResponse.ResidentResponseBuilder residentResponse = ResidentResponse.builder();

        residentResponse.residentId( resident.getResidentId() );
        residentResponse.name( resident.getName() );
        residentResponse.email( resident.getEmail() );
        residentResponse.phone( resident.getPhone() );
        residentResponse.idCard( resident.getIdCard() );
        residentResponse.dob( resident.getDob() );
        residentResponse.gender( resident.getGender() );
        residentResponse.status( resident.getStatus() );

        return residentResponse.build();
    }

    @Override
    public void updateResident(Resident resident, ResidentUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        resident.setName( request.getName() );
        resident.setEmail( request.getEmail() );
        resident.setPhone( request.getPhone() );
        resident.setIdCard( request.getIdCard() );
        resident.setDob( request.getDob() );
        resident.setGender( request.getGender() );
        resident.setStatus( request.getStatus() );
    }
}

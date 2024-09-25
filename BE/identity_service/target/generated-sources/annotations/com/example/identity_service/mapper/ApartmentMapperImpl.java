package com.example.identity_service.mapper;

import com.example.identity_service.dto.reponse.ApartmentResponse;
import com.example.identity_service.dto.request.ApartmentCreationRequest;
import com.example.identity_service.dto.request.ApartmentUpdateRequest;
import com.example.identity_service.entity.Apartment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class ApartmentMapperImpl implements ApartmentMapper {

    @Override
    public Apartment toApartment(ApartmentCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Apartment.ApartmentBuilder apartment = Apartment.builder();

        apartment.buildingName( request.getBuildingName() );
        apartment.unitNumber( request.getUnitNumber() );
        apartment.area( request.getArea() );
        apartment.floor( request.getFloor() );
        apartment.numRoom( request.getNumRoom() );
        apartment.status( request.getStatus() );
        apartment.note( request.getNote() );

        return apartment.build();
    }

    @Override
    public ApartmentResponse toApartmentResponse(Apartment apartment) {
        if ( apartment == null ) {
            return null;
        }

        ApartmentResponse.ApartmentResponseBuilder apartmentResponse = ApartmentResponse.builder();

        apartmentResponse.apartmentId( apartment.getApartmentId() );
        apartmentResponse.buildingName( apartment.getBuildingName() );
        apartmentResponse.unitNumber( apartment.getUnitNumber() );
        apartmentResponse.area( apartment.getArea() );
        apartmentResponse.floor( apartment.getFloor() );
        apartmentResponse.numRoom( apartment.getNumRoom() );
        apartmentResponse.status( apartment.getStatus() );
        apartmentResponse.note( apartment.getNote() );

        return apartmentResponse.build();
    }

    @Override
    public void updateApartment(Apartment apartment, ApartmentUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        apartment.setBuildingName( request.getBuildingName() );
        apartment.setArea( request.getArea() );
        apartment.setFloor( request.getFloor() );
        apartment.setNumRoom( request.getNumRoom() );
        apartment.setStatus( request.getStatus() );
        apartment.setNote( request.getNote() );
    }
}

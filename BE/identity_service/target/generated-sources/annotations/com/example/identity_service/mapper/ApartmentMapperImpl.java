package com.example.identity_service.mapper;

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
        if ( request.getFloor() != null ) {
            apartment.floor( request.getFloor() );
        }
        if ( request.getNumRooms() != null ) {
            apartment.numRooms( request.getNumRooms() );
        }
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

        apartmentResponse.buildingName( apartment.getBuildingName() );
        apartmentResponse.unitNumber( apartment.getUnitNumber() );
        apartmentResponse.area( apartment.getArea() );
        apartmentResponse.floor( apartment.getFloor() );
        apartmentResponse.numRooms( apartment.getNumRooms() );
        apartmentResponse.status( apartment.getStatus() );
        apartmentResponse.note( apartment.getNote() );

        return apartmentResponse.build();
    }
}

package com.example.identity_service.dto.request;

import com.example.identity_service.enums.ApartmentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillCreationRequest {
    String buildingName;
    String unitNumber;
    double area;
    int floor;
    int numRoom;
    ApartmentStatus status;
    String note;
}
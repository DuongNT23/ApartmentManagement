package com.example.identity_service.dto.reponse;

import com.example.identity_service.entity.Role;
import com.example.identity_service.enums.ApartmentStatus;
import com.example.identity_service.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApartmentResponse {
    String apartmentId;
    String buildingName;
    String unitNumber;
    double area;
    int floor;
    int numRoom;
    ApartmentStatus status;
    String note;
}

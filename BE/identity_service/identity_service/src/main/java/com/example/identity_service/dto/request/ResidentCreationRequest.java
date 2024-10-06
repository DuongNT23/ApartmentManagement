package com.example.identity_service.dto.request;

import com.example.identity_service.enums.ResidentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResidentCreationRequest {
    String name;
    String email;
    String phone;
    String idCard;
    LocalDate dob;
    Boolean gender;
    ResidentStatus status;
    String apartmentUnitNum;
    String username;
}
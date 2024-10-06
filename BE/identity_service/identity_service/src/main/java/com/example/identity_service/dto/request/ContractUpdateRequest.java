package com.example.identity_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContractUpdateRequest {
    String userId;
    String apartmentId;
    String residentId;
    LocalDate startDate;
    LocalDate endDate;
    String contractStatus;
    Boolean isRepresentative;
}
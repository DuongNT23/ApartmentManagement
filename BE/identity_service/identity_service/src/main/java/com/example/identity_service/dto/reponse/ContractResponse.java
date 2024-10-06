package com.example.identity_service.dto.reponse;

import com.example.identity_service.entity.Apartment;
import com.example.identity_service.entity.Resident;
import com.example.identity_service.entity.User;
import com.example.identity_service.enums.ContractStatus;
import com.example.identity_service.enums.ResidentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContractResponse {
    String contractId;
    User user;
    Apartment apartment;
    Resident resident;
    LocalDate startDate;
    LocalDate endDate;
    ContractStatus contractStatus;
    Boolean isRepresentative;

}

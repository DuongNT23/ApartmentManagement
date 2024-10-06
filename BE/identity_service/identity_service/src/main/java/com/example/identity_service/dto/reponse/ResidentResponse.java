package com.example.identity_service.dto.reponse;

import com.example.identity_service.entity.Apartment;
import com.example.identity_service.enums.BillType;
import com.example.identity_service.enums.PaymentStatus;
import com.example.identity_service.enums.ResidentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResidentResponse {
    String residentId;
    String name;
    String email;
    String phone;
    String idCard;
    LocalDate dob;
    Boolean gender;
    ResidentStatus status;
}

package com.example.identity_service.dto.request;

import com.example.identity_service.enums.ApartmentStatus;
import com.example.identity_service.enums.BillType;
import com.example.identity_service.enums.PaymentStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillUpdateRequest {
    String apartmentUnitNum;
    BillType billType;
    float usageAmount;
    float totalAmount;
    LocalDate dueDate;
    PaymentStatus paymentStatus;
    String note;
}
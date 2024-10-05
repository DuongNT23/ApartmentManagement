package com.example.identity_service.dto.reponse;

import com.example.identity_service.entity.Apartment;
import com.example.identity_service.enums.ApartmentStatus;
import com.example.identity_service.enums.BillType;
import com.example.identity_service.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BillResponse {
    String billingId;
    Apartment apartment;
    BillType billType;
    float usageAmount;
    Float totalAmount;
    LocalDate createdDate;
    LocalDate dueDate;
    PaymentStatus paymentStatus;
    String note;
}

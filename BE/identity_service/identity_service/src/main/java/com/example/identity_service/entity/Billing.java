package com.example.identity_service.entity;

import com.example.identity_service.enums.BillType;
import com.example.identity_service.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "billing")
@SQLRestriction("is_delete = 0")
public class Billing {

    @Id
    @Column(name = "billing_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    String billingId;

    @ManyToOne
    @JoinColumn(name = "apartment_id", nullable = false)
    Apartment apartment;

    @Enumerated(EnumType.STRING)
    @Column(name = "bill_type", nullable = false)
    BillType billType;

    @Column(name = "usage_amount", nullable = false)
    float usageAmount;

    @Column(name = "total_amount")
    float totalAmount;

    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    LocalDate createdDate;

    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    PaymentStatus paymentStatus;

    @Column(name = "note")
    String note;

    @Column(name = "is_delete", columnDefinition = "TINYINT(1)")
    boolean isDelete;
}
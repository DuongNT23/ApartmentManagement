package com.example.identity_service.entity;

import com.example.identity_service.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "contracts")
@Where(clause = "contract_status != 'terminated'")
public class Contract {

    @Id
    @Column(name = "contract_id", nullable = false, length = 255)
    @GeneratedValue(strategy = GenerationType.UUID)
    String contractId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "apartment_id", referencedColumnName = "apartment_id")
    Apartment apartment;

    @ManyToOne
    @JoinColumn(name = "resident_id", referencedColumnName = "resident_id")
    Resident resident;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    LocalDate startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "contract_status", nullable = false)
    ContractStatus contractStatus;

    @Column(name = "is_representative", nullable = false)
    Boolean isRepresentative = false;
}

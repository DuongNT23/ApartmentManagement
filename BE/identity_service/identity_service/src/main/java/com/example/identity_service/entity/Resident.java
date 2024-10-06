package com.example.identity_service.entity;

import com.example.identity_service.enums.ResidentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "residents")
@Where(clause = "status != 'former'")
public class Resident {

    @Id
    @Column(name = "resident_id", nullable = false, length = 255)
    @GeneratedValue(strategy = GenerationType.UUID)
    String residentId;

    @Column(name = "name", nullable = false, length = 255)
    String name;

    @Column(name = "email", nullable = false, length = 255)
    String email;

    @Column(name = "phone", length = 20)
    String phone;

    @Column(name = "id_card", length = 50)
    String idCard;

    @Column(name = "dob")
    LocalDate dob;

    @Column(name = "gender", columnDefinition = "TINYINT(1)")
    Boolean gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    ResidentStatus status = ResidentStatus.current;
}

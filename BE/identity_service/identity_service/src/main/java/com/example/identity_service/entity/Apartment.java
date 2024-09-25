package com.example.identity_service.entity;

import com.example.identity_service.enums.ApartmentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "apartment_id", nullable = false, unique = true)
    String apartmentId;

    @Column(name = "building_name", nullable = false)
    String buildingName;

    @Column(name = "unit_number", nullable = false)
    String unitNumber;

    @Column(name = "area", nullable = false)
    double area;

    @Column(name = "floor", nullable = false)
    int floor;

    @Column(name = "num_rooms", nullable = false)
    int numRoom;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    ApartmentStatus status;

    @Column(name = "note", nullable = true)
    String note;
}

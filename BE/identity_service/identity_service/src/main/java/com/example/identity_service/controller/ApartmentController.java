package com.example.identity_service.controller;

import com.example.identity_service.dto.reponse.ApartmentResponse;
import com.example.identity_service.dto.reponse.UserResponse;
import com.example.identity_service.dto.request.*;
import com.example.identity_service.service.ApartmentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081/")
@RestController
@RequestMapping("/apartments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApartmentController {
    ApartmentService apartmentService;

    @PostMapping
    ApiResponse<ApartmentResponse> createApartment(@RequestBody @Valid ApartmentCreationRequest request){
        log.info("Controller: Create apartment");
        return ApiResponse.<ApartmentResponse>builder()
                .result(apartmentService.createRequest(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<ApartmentResponse>> getApartments(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size ){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Pageable pageable = PageRequest.of(page, size);

        log.info("Username: {}" , authentication.getName());

        return ApiResponse.<List<ApartmentResponse>>builder()
                .result(apartmentService.getApartment(pageable)).build();
    }

    @GetMapping("/total")
    ApiResponse<Long> getTotalApartment( ){
        return ApiResponse.<Long>builder()
                .result(apartmentService.getTotalApartment()).build();
    }

    @PutMapping("/{aparmentId}")
    ApiResponse<ApartmentResponse> updateApartment(@PathVariable String aparmentId, @RequestBody ApartmentUpdateRequest request){
        return ApiResponse.<ApartmentResponse>builder()
                .result(apartmentService.updateApartment(aparmentId, request))
                .build();
    }

    @GetMapping("/{aparmentId}")
    ApiResponse<ApartmentResponse> getApartmentById(@PathVariable String aparmentId){
        return ApiResponse.<ApartmentResponse>builder()
                .result(apartmentService.getApartmentById(aparmentId))
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<ApartmentResponse>> searchApartments(
            @RequestParam(required = false) String unitNumber,
            @RequestParam(required = false) String floor,
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String numRoom) {
        return ApiResponse.<List<ApartmentResponse>>builder()
                .result(apartmentService.searchApartments(unitNumber, floor, area, status, numRoom))
                .build();
    }

    @GetMapping("/unitNumber")
    public ApiResponse<List<String>> getAllUnitNumber() {
        return ApiResponse.<List<String>>builder()
                .result(apartmentService.getAllUnitNumber())
                .build();
    }

}

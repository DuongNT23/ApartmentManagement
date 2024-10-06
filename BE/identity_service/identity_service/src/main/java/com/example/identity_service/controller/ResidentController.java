package com.example.identity_service.controller;

import com.example.identity_service.dto.reponse.BillResponse;
import com.example.identity_service.dto.reponse.ContractResponse;
import com.example.identity_service.dto.reponse.ResidentResponse;
import com.example.identity_service.dto.request.*;
import com.example.identity_service.enums.ResidentStatus;
import com.example.identity_service.service.BillService;
import com.example.identity_service.service.ResidentService;
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
@RequestMapping("/residents")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ResidentController {
    ResidentService residentService;

    @PostMapping
    ApiResponse<ResidentResponse> createResident(@RequestBody @Valid ResidentCreationRequest request){
        log.info("Controller: Create resident");
        return ApiResponse.<ResidentResponse>builder()
                .result(residentService.createRequest(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<ResidentResponse>> getResidents(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size ){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Pageable pageable = PageRequest.of(page, size);

        log.info("Username: {}" , authentication.getName());

        return ApiResponse.<List<ResidentResponse> >builder()
                .result(residentService.getAllResidents(pageable))
                .build();
    }

    @GetMapping("/total")
    ApiResponse<Long> getTotalResident( ){
        return ApiResponse.<Long>builder()
                .result(residentService.getTotalResident()).build();
    }

    @PutMapping("/{residentId}")
    ApiResponse<ResidentResponse> updateResident(@PathVariable String residentId, @RequestBody ResidentUpdateRequest request){
        return ApiResponse.<ResidentResponse>builder()
                .result(residentService.updateResident(residentId, request))
                .build();
    }

    @DeleteMapping("/{residentId}")
    ApiResponse<String> deleteResident(@PathVariable String residentId){
        return ApiResponse.<String>builder()
                .result(residentService.deleteResident(residentId))
                .build();
    }

    @GetMapping("/{residentId}")
    ApiResponse<ResidentResponse> getResidentsById(@PathVariable String residentId){
        return ApiResponse.<ResidentResponse>builder()
                .result(residentService.getResidentById(residentId))
                .build();
    }

    @GetMapping("getResidentByApartment/{apartmentId}")
    ApiResponse<List<ResidentResponse> > getResidentByApartment(@PathVariable String apartmentId){
        return ApiResponse.<List<ResidentResponse>>builder()
                .result(residentService.getResidentByApartment(apartmentId))
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<ResidentResponse>> searchResidents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) ResidentStatus status) {
        return ApiResponse.<List<ResidentResponse>>builder()
                .result(residentService.searchResidents(name, status))
                .build();
    }

}

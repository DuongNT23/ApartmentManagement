package com.example.identity_service.controller;

import com.example.identity_service.dto.reponse.BillResponse;
import com.example.identity_service.dto.reponse.ContractResponse;
import com.example.identity_service.dto.reponse.ResidentResponse;
import com.example.identity_service.dto.request.*;
import com.example.identity_service.service.ContractService;
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
@RequestMapping("/contracts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ContractController {
    ContractService contractService;

    @PostMapping()
    ApiResponse<ContractResponse> createContract(@RequestBody @Valid ContractCreationRequest request){
        log.info("Controller: Create contract");
        return ApiResponse.<ContractResponse>builder()
                .result(contractService.createRequest(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<ContractResponse>> getBills(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size ){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Pageable pageable = PageRequest.of(page, size);

        log.info("Username: {}" , authentication.getName());

        return ApiResponse.<List<ContractResponse> >builder()
                .result(contractService.getAllContracts(pageable))
                .build();
    }

    @GetMapping("/total")
    ApiResponse<Long> getTotalBill( ){
        return ApiResponse.<Long>builder()
                .result(contractService.getTotalContract()).build();
    }

    @PutMapping("/{contractId}")
    ApiResponse<ContractResponse> updateContract(@PathVariable String contractId, @RequestBody ContractUpdateRequest request){
        return ApiResponse.<ContractResponse>builder()
                .result(contractService.updateContract(contractId, request))
                .build();
    }

    @DeleteMapping ("/{contractId}")
    ApiResponse<String> deleteContract(@PathVariable String contractId){
        return ApiResponse.<String>builder()
                .result(contractService.deleteContract(contractId))
                .build();
    }

    @GetMapping("getApartmentByUser/{userId}")
    ApiResponse<String> getApartmentByUser(@PathVariable String userId){
        return ApiResponse.<String>builder()
                .result(contractService.getApartmentByUser(userId))
                .build();
    }

    @GetMapping("getContractByResident/{residentId}")
    ApiResponse<ContractResponse> getContractByResidentId(@PathVariable String residentId){
        var test = contractService.getContractByResident(residentId);
        return ApiResponse.<ContractResponse>builder()
                .result(contractService.getContractByResident(residentId))
                .build();
    }

//    @GetMapping("/search")
//    public ApiResponse<List<BillResponse>> searchApartments(
//            @RequestParam(required = false) String unitNumber,
//            @RequestParam(required = false) String floor,
//            @RequestParam(required = false) String area,
//            @RequestParam(required = false) String status,
//            @RequestParam(required = false) String numRoom) {
//        return ApiResponse.<List<BillResponse>>builder()
//                .result(billService.searchApartments(unitNumber, floor, area, status, numRoom))
//                .build();
//    }

}

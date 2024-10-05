package com.example.identity_service.controller;

import com.example.identity_service.dto.reponse.BillResponse;
import com.example.identity_service.dto.request.*;
import com.example.identity_service.service.BillService;
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
@RequestMapping("/billings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BillController {
    BillService billService;

    @PostMapping()
    ApiResponse<BillResponse> createBill(@RequestBody @Valid BillCreationRequest request){
        log.info("Controller: Create bill");
        return ApiResponse.<BillResponse>builder()
                .result(billService.createRequest(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<BillResponse>> getBills(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size ){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Pageable pageable = PageRequest.of(page, size);

        log.info("Username: {}" , authentication.getName());

        return ApiResponse.<List<BillResponse> >builder()
                .result(billService.getAllBills(pageable))
                .build();
    }

    @GetMapping("/total")
    ApiResponse<Long> getTotalBill( ){
        return ApiResponse.<Long>builder()
                .result(billService.getTotalBill()).build();
    }

    @PutMapping("/{billingId}")
    ApiResponse<BillResponse> updateBill(@PathVariable String billingId, @RequestBody BillUpdateRequest request){
        return ApiResponse.<BillResponse>builder()
                .result(billService.updateBill(billingId, request))
                .build();
    }

    @GetMapping("/{apartmentId}")
    ApiResponse<List<BillResponse>> getBillByApartmentId(@PathVariable String apartmentId){
        return ApiResponse.<List<BillResponse>>builder()
                .result(billService.getBillByApartmentId(apartmentId))
                .build();
    }

    @DeleteMapping ("/{billingId}")
    ApiResponse<String> deleteBill(@PathVariable String billingId){
        return ApiResponse.<String>builder()
                .result(billService.deleteBill(billingId))
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

package com.example.identity_service.service;

import com.example.identity_service.dto.reponse.BillResponse;
import com.example.identity_service.dto.request.BillCreationRequest;
import com.example.identity_service.dto.request.BillUpdateRequest;
import com.example.identity_service.entity.Apartment;
import com.example.identity_service.entity.Billing;
import com.example.identity_service.enums.BillType;
import com.example.identity_service.enums.PaymentStatus;
import com.example.identity_service.exception.AppException;
import com.example.identity_service.exception.ErrorCode;
import com.example.identity_service.mapper.BillMapper;
import com.example.identity_service.repository.ApartmentRepository;
import com.example.identity_service.repository.BillRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BillService {
    BillRepository billRepository;
    BillMapper billMapper;
    ApartmentRepository apartmentRepository;

    public BillResponse createRequest(BillCreationRequest request) {
        log.info("Service: Create bill");

        if(request.getCreatedDate() == null){
            LocalDate today = LocalDate.now();
            request.setCreatedDate(today);
        }

        if(request.getDueDate().isBefore( request.getCreatedDate())){
            throw new AppException(ErrorCode.DUE_DATE_VALID);
        }

        if(existingBill(request.getBillType(), request.getDueDate(), request.getApartmentId())){
            throw new AppException(ErrorCode.BILL_DUPLICATED);
        }


        Billing billing = billMapper.toBill(request);

        Apartment apartment = apartmentRepository.findByunitNumber(request.getApartmentId());
        billing.setApartment(apartment);

        return billMapper.toBillResponse(billRepository.save(billing));
    }

    private boolean existingBill(BillType billType, LocalDate dueDate, String apartmentId) {
        int month = dueDate.getMonthValue();
        int year = dueDate.getYear();

        List<Billing> bill =  billRepository.findBill(apartmentId, billType, month, year);
        if(CollectionUtils.isEmpty(bill)){
            return false;
        }
        return true;
    }

    public List<BillResponse> getAllBills(Pageable pageable) {
        log.info("Service: get all bills");
        return billRepository.findAll(pageable).stream().map(billMapper::toBillResponse).toList();
    }

    public Long getTotalBill() {
        return billRepository.count();
    }

    public BillResponse updateBill(String billingId, BillUpdateRequest request) {
        Billing billing = billRepository.findById(billingId).orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_EXISTED));

        billMapper.updateBill( billing ,request);
        return billMapper.toBillResponse(billRepository.save(billing));
    }

    public List<BillResponse> getBillByApartmentId(String apartmentId) {
        return billRepository.findByApartmentApartmentIdAndPaymentStatus(apartmentId, PaymentStatus.unpaid).stream().map(billMapper::toBillResponse).toList();
    }

    public String deleteBill(String billingId) {
        var test = billRepository.findById(billingId);
        Billing billing = billRepository.findById(billingId).orElseThrow(() -> new AppException(ErrorCode.BILL_NOT_EXISTED));
        billing.setDelete(true);
        billRepository.save(billing);
        return "Detele successfully";
    }
}


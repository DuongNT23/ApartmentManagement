package com.example.identity_service.mapper;

import com.example.identity_service.dto.reponse.BillResponse;
import com.example.identity_service.dto.request.BillCreationRequest;
import com.example.identity_service.dto.request.BillUpdateRequest;
import com.example.identity_service.entity.Billing;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class BillMapperImpl implements BillMapper {

    @Override
    public Billing toBill(BillCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        Billing.BillingBuilder billing = Billing.builder();

        billing.billType( request.getBillType() );
        billing.usageAmount( request.getUsageAmount() );
        billing.totalAmount( request.getTotalAmount() );
        billing.createdDate( request.getCreatedDate() );
        billing.dueDate( request.getDueDate() );
        billing.paymentStatus( request.getPaymentStatus() );
        billing.note( request.getNote() );

        return billing.build();
    }

    @Override
    public BillResponse toBillResponse(Billing billing) {
        if ( billing == null ) {
            return null;
        }

        BillResponse.BillResponseBuilder billResponse = BillResponse.builder();

        billResponse.billingId( billing.getBillingId() );
        billResponse.apartment( billing.getApartment() );
        billResponse.billType( billing.getBillType() );
        billResponse.usageAmount( billing.getUsageAmount() );
        billResponse.totalAmount( billing.getTotalAmount() );
        billResponse.createdDate( billing.getCreatedDate() );
        billResponse.dueDate( billing.getDueDate() );
        billResponse.paymentStatus( billing.getPaymentStatus() );
        billResponse.note( billing.getNote() );

        return billResponse.build();
    }

    @Override
    public void updateBill(Billing billing, BillUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        billing.setBillType( request.getBillType() );
        billing.setUsageAmount( request.getUsageAmount() );
        billing.setTotalAmount( request.getTotalAmount() );
        billing.setDueDate( request.getDueDate() );
        billing.setPaymentStatus( request.getPaymentStatus() );
        billing.setNote( request.getNote() );
    }
}

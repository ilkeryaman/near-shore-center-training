package com.nsc.billing.controller;

import com.nsc.billing.enums.response.ResponseCode;
import com.nsc.billing.enums.response.ResponseMessage;
import com.nsc.billing.model.account.CustomerAccount;
import com.nsc.billing.model.response.BillingResponse;
import com.nsc.billing.service.IBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/billing-api")
public class BillingController {
    @Autowired
    private IBillingService billingService;

    @GetMapping("/accounts")
    public ResponseEntity<BillingResponse> getCustomerAccount(@RequestParam(required = false) String customerNo, @RequestParam(required = false) Long addressId){
        List<CustomerAccount> listOfCustomerAccounts = null;
        if(StringUtils.hasLength(customerNo)){
            listOfCustomerAccounts = billingService.findByCustomerNo(customerNo);
        } else if (addressId != null){
            listOfCustomerAccounts = billingService.findByAddressId(addressId);
        }
        return ResponseEntity.status(HttpStatus.OK).body(new BillingResponse(ResponseCode.OK.getValue(), ResponseMessage.OK.getValue(), listOfCustomerAccounts));
    }
}

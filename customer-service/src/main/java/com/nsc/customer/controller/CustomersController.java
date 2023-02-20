package com.nsc.customer.controller;

import com.nsc.customer.enums.response.ResponseCode;
import com.nsc.customer.enums.response.ResponseMessage;
import com.nsc.customer.exception.CustomerNotFoundException;
import com.nsc.customer.model.customer.Customer;
import com.nsc.customer.model.response.CustomerResponse;
import com.nsc.customer.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer-api")
public class CustomersController {

    @Resource(name = "customerFileService")
    ICustomerService customerService;

    @Operation(summary = "Gets list of customers.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content)
    })
    @GetMapping("/customers")
    public ResponseEntity<CustomerResponse>  getCustomers(){
        List<Customer> listOfCustomers = customerService.getCustomerList();
        return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponse<List<Customer>>(ResponseCode.OK.getValue(), ResponseMessage.OK.getValue(), listOfCustomers));
    }

    @Operation(summary = "Gets customer by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)
    })
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerResponse>  getCustomerById(@PathVariable long id){
        Customer customer = customerService.findCustomerById(id);
        if(customer == null){
            throw new CustomerNotFoundException(ResponseMessage.CUSTOMER_NOT_FOUND.getValue());
        }
        return ResponseEntity.status(HttpStatus.OK).body(new CustomerResponse<Customer>(ResponseCode.OK.getValue(), ResponseMessage.OK.getValue(), customer));
    }

    @Operation(summary = "Adds a new customer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = @Content),
    })
    @PostMapping("/customers")
    public ResponseEntity<CustomerResponse> addCustomer(@Valid @RequestBody Customer customer){
        boolean result = customerService.addCustomer(customer);
        return result
                ? ResponseEntity.status(HttpStatus.CREATED).body(new CustomerResponse<Customer>(ResponseCode.OK.getValue(), ResponseMessage.OK.getValue(), null))
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomerResponse<Customer>(ResponseCode.GENERAL_ERROR.getValue(),
                ResponseMessage.GENERAL_ERROR.getValue(), null));
    }

    @Operation(summary = "Deletes a customer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = @Content),
    })
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable long id){
        boolean result = customerService.deleteCustomer(id);
        return result
            ? ResponseEntity.status(HttpStatus.OK).body(new CustomerResponse<Customer>(ResponseCode.OK.getValue(), ResponseMessage.OK.getValue(), null))
            : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CustomerResponse<Customer>(ResponseCode.GENERAL_ERROR.getValue(),
                    ResponseMessage.GENERAL_ERROR.getValue(), null));
    }
}

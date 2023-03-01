package com.nsc.address.controller;

import com.nsc.address.enums.response.ResponseCode;
import com.nsc.address.enums.response.ResponseMessage;
import com.nsc.address.model.address.Address;
import com.nsc.address.model.response.AddressResponse;
import com.nsc.address.service.IAddressService;
import com.nsc.address.exception.AddressNotFoundException;
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
@RequestMapping("/address-api")
public class AddressController {

    @Resource(name = "addressFileService")
    private IAddressService addressService;

    @Operation(summary = "Gets list of addresses.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AddressResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content)
    })
    @GetMapping("/addresses")
    public ResponseEntity<AddressResponse>  getAddresses(){
        List<Address> listOfAddresses = addressService.getAddressList();
        return ResponseEntity.status(HttpStatus.OK).body(new AddressResponse<List<Address>>(ResponseCode.OK.getValue(), ResponseMessage.OK.getValue(), listOfAddresses));
    }

    @Operation(summary = "Gets address by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AddressResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)
    })
    @GetMapping("/addresses/{id}")
    public ResponseEntity<AddressResponse>  getAddressById(@PathVariable long id){
        Address address = addressService.findAddressById(id);
        if(address == null){
            throw new AddressNotFoundException(ResponseMessage.ADDRESS_NOT_FOUND.getValue());
        }
        return ResponseEntity.status(HttpStatus.OK).body(new AddressResponse<Address>(ResponseCode.OK.getValue(), ResponseMessage.OK.getValue(), address));
    }

    @Operation(summary = "Adds a new address.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AddressResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = @Content),
    })
    @PostMapping("/addresses")
    public ResponseEntity<AddressResponse> addAddress(@Valid @RequestBody Address address){
        boolean result = addressService.addAddress(address);
        return result
                ? ResponseEntity.status(HttpStatus.CREATED).body(new AddressResponse<Address>(ResponseCode.OK.getValue(), ResponseMessage.OK.getValue(), null))
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AddressResponse<Address>(ResponseCode.GENERAL_ERROR.getValue(),
                ResponseMessage.GENERAL_ERROR.getValue(), null));
    }

    @Operation(summary = "Deletes an address.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AddressResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = @Content),
    })
    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<AddressResponse> deleteAddress(@PathVariable long id){
        boolean result = addressService.deleteAddress(id);
        return result
            ? ResponseEntity.status(HttpStatus.OK).body(new AddressResponse<Address>(ResponseCode.OK.getValue(), ResponseMessage.OK.getValue(), null))
            : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AddressResponse<Address>(ResponseCode.GENERAL_ERROR.getValue(),
                    ResponseMessage.GENERAL_ERROR.getValue(), null));
    }
}

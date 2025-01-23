package com.revision.demo.controller;

import com.revision.demo.dto.CustomerDto;
import com.revision.demo.dto.ErrorResponseDto;
import com.revision.demo.dto.ResponseDto;
import com.revision.demo.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for Accounts",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {

    private IAccountsService iAccountsService;


    @GetMapping("/demo")
    public String demoRoute(){
        return "Spring Boot revision time2";
    }

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer & Account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
            content = @Content(
            schema = @Schema(implementation = ErrorResponseDto.class)
            )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
        iAccountsService.createAccount(customerDto);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED,"Account created successfully"));
    }

    @Operation(
            summary = "Fetch Account Details",
            description = "REST API to fetch account details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "302",
                    description = "HTTP Status FOUND"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @GetMapping("/my-account/{mobileNo}")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@PathVariable String mobileNo){
        CustomerDto customerDto = iAccountsService.findAccountByMobileNo(mobileNo);
        return  ResponseEntity
                .status(HttpStatus.FOUND)
                .body(customerDto);
    }

    @PutMapping("/my-account/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@RequestBody CustomerDto customerDto){
        boolean isUpdated=iAccountsService.updateAccount(customerDto);
        if (isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(HttpStatus.OK,"Account Details successfully updated"));
        }else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(HttpStatus.BAD_REQUEST,"Account Details cannot be updated"));
        }
    }

    @DeleteMapping("/my-account/{mobileNo}")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@PathVariable String mobileNo){
        boolean isDeleted=iAccountsService.deleteAccount(mobileNo);
        if (isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(HttpStatus.OK,"Account Details successfully deleted"));
        }else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(HttpStatus.BAD_REQUEST,"Account Details cannot be deleted"));
        }
    }
}

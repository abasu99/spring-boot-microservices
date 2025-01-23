package com.revision.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(description = "Name of customer", example = "Arnab Basu")
    private String name;

    @Schema(description = "Email-ID of customer", example = "asrbaban@gmail.com")
    private String email;

    @Schema(description = "Mobile no of customer", example = "8865409234")
    private String mobileNo;

    @Schema(description = "Account details of customer")
    private AccountsDto accountsDto;
}

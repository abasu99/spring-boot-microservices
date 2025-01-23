package com.revision.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Accounts", description = "Schema to hold Account information")
public class AccountsDto {

    @Schema(description = "Account no of customer", example = "330012918281")
    private Long accountNo;

    @Schema(description = "Account Type", example = "Savings")
    private String accountType;

    @Schema(description = "Branch address of bank", example = "123, London Street, UK")
    private String branchAddress;
}

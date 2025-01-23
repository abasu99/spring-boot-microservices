package com.revision.demo.mapper;

import com.revision.demo.dto.AccountsDto;
import com.revision.demo.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(Accounts accounts,AccountsDto accountsDto){
       accountsDto.setAccountNo(accounts.getAccountNo());
       accountsDto.setAccountType(accounts.getAccountType());
       accountsDto.setBranchAddress(accounts.getBranchAddress());
       return accountsDto;
    }

    public static Accounts mapToAccounts(Accounts accounts,AccountsDto accountsDto){
        accounts.setAccountNo(accountsDto.getAccountNo());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}

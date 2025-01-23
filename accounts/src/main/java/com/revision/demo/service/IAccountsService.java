package com.revision.demo.service;

import com.revision.demo.dto.CustomerDto;

public interface IAccountsService {

    public void createAccount(CustomerDto customerDto);
    public CustomerDto findAccountByMobileNo(String mobileNo);
    public boolean updateAccount(CustomerDto customerDto);
    public boolean deleteAccount(String mobileNumber);

}

package com.revision.demo.mapper;

import com.revision.demo.dto.CustomerDto;
import com.revision.demo.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer,CustomerDto customerDto){
       customerDto.setName(customer.getName());
       customerDto.setEmail(customer.getEmail());
       customerDto.setMobileNo(customer.getMobileNo());
       return customerDto;
    }

    public static Customer mapToCustomer(Customer customer,CustomerDto customerDto){
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNo(customerDto.getMobileNo());
        return customer;
    }
}

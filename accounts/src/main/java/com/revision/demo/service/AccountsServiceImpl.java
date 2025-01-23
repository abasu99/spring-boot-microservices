package com.revision.demo.service;

import com.revision.demo.dto.AccountsDto;
import com.revision.demo.dto.CustomerDto;
import com.revision.demo.entity.Accounts;
import com.revision.demo.entity.Customer;
import com.revision.demo.exception.CustomerExistsException;
import com.revision.demo.exception.ResourceNotFoundException;
import com.revision.demo.mapper.AccountsMapper;
import com.revision.demo.mapper.CustomerMapper;
import com.revision.demo.repository.AccountsRepository;
import com.revision.demo.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService{

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(new Customer(),customerDto);
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNo(customer.getMobileNo());
        System.out.println("Optional Customer -> "+(optionalCustomer.isPresent()?optionalCustomer.get():false));
        if (optionalCustomer.isPresent()){
            throw new CustomerExistsException("Customer with mobile no - "+ customer.getMobileNo()+" already holds an account");
        }
//        customer.setCreatedAt(LocalDateTime.now());
//        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto findAccountByMobileNo(String mobileNo) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNo(mobileNo);
//        if(optionalCustomer.isPresent()){
            Accounts accounts = accountsRepository.findByCustomerId(optionalCustomer.get().getCustomerId());
//        }
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(optionalCustomer.get(),new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNo()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNo().toString())
            );
            AccountsMapper.mapToAccounts(accounts,accountsDto);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customer,customerDto);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Optional<Customer> optionalCustomer = Optional.ofNullable(customerRepository.findByMobileNo(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        ));
        accountsRepository.deleteByCustomerId(optionalCustomer.get().getCustomerId());
        customerRepository.deleteById(optionalCustomer.get().getCustomerId());
        return true;
    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNo= 100000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNo(randomAccNo);
        newAccount.setAccountType("Savings");
        newAccount.setBranchAddress("58, Spine Street, Madrid, Spain");
//        newAccount.setCreatedAt(LocalDateTime.now());
//        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }


}

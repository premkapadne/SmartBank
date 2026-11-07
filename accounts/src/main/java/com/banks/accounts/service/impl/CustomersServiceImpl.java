package com.banks.accounts.service.impl;

import com.banks.accounts.dto.AccountsDto;
import com.banks.accounts.dto.CardsDto;
import com.banks.accounts.dto.CustomerDetailsDto;
import com.banks.accounts.dto.LoansDto;
import com.banks.accounts.entity.Accounts;
import com.banks.accounts.entity.Customer;
import com.banks.accounts.exception.ResourceNotFoundException;
import com.banks.accounts.mapper.AccountsMapper;
import com.banks.accounts.mapper.CustomerMapper;
import com.banks.accounts.repository.AccountsRepository;
import com.banks.accounts.repository.CustomerRepository;
import com.banks.accounts.service.ICustomersService;
import com.banks.accounts.service.client.CardsClient;
import com.banks.accounts.service.client.LoansClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsClient cardsClient;
    private LoansClient loansClient;


    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansClient.fetchLoanDetails(correlationId,mobileNumber);
        if(loansDtoResponseEntity != null) {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }
        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsClient.fetchCardDetails(correlationId,mobileNumber);
        if (cardsDtoResponseEntity != null) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }

        return customerDetailsDto;

    }
}

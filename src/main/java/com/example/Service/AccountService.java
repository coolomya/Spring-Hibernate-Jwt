package com.example.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Models.AccountDTO;
import com.example.Repo.AccountRepository;
import com.example.exception.ResourceNotFoundException;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountDTO createAccount(AccountDTO account) {
        return accountRepository.save(account);
    }

    public AccountDTO updateAccount(int id, AccountDTO accountDetails) {
    	
    	Optional<AccountDTO> accountOptional = Optional.ofNullable(accountRepository.findById(id));
    	accountOptional.orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
    	accountOptional.get().setAccountName(accountDetails.getAccountName());;
        return accountRepository.save(accountOptional.get());
    }

    public AccountDTO getAccountById(int id) {
    	Optional<AccountDTO> accountOptional = Optional.ofNullable(accountRepository.findById(id));
    	accountOptional.orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
        return accountOptional.get();
    }

    public List<AccountDTO> getAllAccounts() {
        return (List<AccountDTO>) accountRepository.findAll();
    }

}


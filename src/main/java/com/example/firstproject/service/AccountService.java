package com.example.firstproject.service;

import com.example.firstproject.dto.AccountForm;
import com.example.firstproject.entity.Account;
import com.example.firstproject.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public Long createUser(AccountForm form){
        Account account = form.toEntity();
        accountRepository.save(account);
        return account.getId();
    }
}

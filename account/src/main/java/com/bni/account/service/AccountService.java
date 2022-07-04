package com.bni.account.service;

import com.bni.account.dto.EditRequest;
import com.bni.account.dto.TransferRequest;
import com.bni.account.entity.Account;
import com.bni.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    public void editAccount(EditRequest editRequest) {
        Account account = accountRepository.findByAccountNumber(editRequest.getAccountNumber());
        account.setAccountName(editRequest.getAccountName());
        accountRepository.save(account);
    }

    @Transactional
    public void deleteAccount(String accountNumber) throws Exception {
        long deleteStatus = accountRepository.deleteByAccountNumber(accountNumber);
        if (deleteStatus != 1) {
            throw new Exception("Delete Failed for "+ accountNumber);
        }
    }

    @Transactional
    public Map<String, Object> updateBalance(TransferRequest transferRequest) throws Exception {
        Account accountSender = accountRepository.findByAccountNumber(transferRequest.getAccountNumberSender());
        Account accountReceiver = accountRepository.findByAccountNumber(transferRequest.getAccountNumberReceiver());
        if (transferRequest.getAmount() <= 0) throw new Exception("Transfer amount invalid");
        if (accountSender.getAccountBalance() < transferRequest.getAmount()) throw new Exception("Insufficient sender balance");
        accountSender.setAccountBalance(accountSender.getAccountBalance() - transferRequest.getAmount());
        accountReceiver.setAccountBalance(accountReceiver.getAccountBalance() + transferRequest.getAmount());

        accountRepository.save(accountSender);
        accountRepository.save(accountReceiver);
        Map<String, Object> data = new HashMap<>();
        data.put("sender", accountSender);
        data.put("receiver", accountReceiver);

        return data;
    }

}

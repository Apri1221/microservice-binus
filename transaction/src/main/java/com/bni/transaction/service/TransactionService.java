package com.bni.transaction.service;


import com.bni.transaction.dto.TransferRequest;
import com.bni.transaction.entity.Transaction;
import com.bni.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public void transferBalance(TransferRequest transferRequest) throws Exception {
        Date now = new Date();
        Transaction transactionSender = new Transaction(transferRequest.getAccountNumberSender(),
                "Send balance to " + transferRequest.getAccountNumberReceiver(),
                "-"+ transferRequest.getAmount(),
                now);
        Transaction transactionReceiver = new Transaction(transferRequest.getAccountNumberReceiver(),
                "Receive balance from " + transferRequest.getAccountNumberSender(),
                "+"+ transferRequest.getAmount(),
                now);
        transactionRepository.save(transactionSender);
        transactionRepository.save(transactionReceiver);
    }

    public List<Transaction> getTransaction(String accountNumber) {
        return transactionRepository.findByAccountNumber(accountNumber);
    }
}

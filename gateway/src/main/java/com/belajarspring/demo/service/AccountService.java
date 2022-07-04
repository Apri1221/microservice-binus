package com.belajarspring.demo.service;

import com.belajarspring.demo.dto.TransferRequest;
import com.belajarspring.demo.entity.Account;
import com.belajarspring.demo.dto.EditRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;

@Service
public class AccountService {

    private final WebClient webClient;

    public AccountService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    public Object getAccounts() {
        return this.webClient.get()
                .uri("/api/getAccounts")
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    public void addAccount(Account account) {
        this.webClient.put()
                .uri("/api/addAccount")
                .body(BodyInserters.fromValue(account))
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    public void editAccount(EditRequest editRequest) {
        this.webClient.patch()
                .uri("/api/editAccount")
                .body(BodyInserters.fromValue(editRequest))
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Transactional
    public void deleteAccount(String accountNumber) throws Exception {
        this.webClient.delete()
                .uri(String.format("/api/deleteAccount/%s", accountNumber))
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    @Transactional
    public void updateBalance(TransferRequest transferRequest) {
        this.webClient.patch()
                .uri("/api/updateBalance")
                .body(BodyInserters.fromValue(transferRequest))
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}

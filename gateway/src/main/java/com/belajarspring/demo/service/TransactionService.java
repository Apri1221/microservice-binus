package com.belajarspring.demo.service;

import com.belajarspring.demo.dto.TransferRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TransactionService {

    private final WebClient webClient;

    public TransactionService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build();
    }

    public void transferBalance(TransferRequest transferRequest) throws Exception {
        this.webClient.post()
                .uri("/api/transferBalance")
                .body(BodyInserters.fromValue(transferRequest))
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

    public Object getTransaction(String accountNumber) {
        return this.webClient.get()
                .uri(String.format("/api/getTransaction/%s", accountNumber))
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}

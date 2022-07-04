package com.bni.transaction.controller;

import com.bni.transaction.dto.TransferRequest;
import com.bni.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    TransactionService webService;

    @PostMapping("/transferBalance")
    public @ResponseBody
    ResponseEntity<Object> transferBalance(@RequestBody @Validated TransferRequest transferRequest) {
        try {
            webService.transferBalance(transferRequest);
            Map<String, Object> output = new HashMap<>();
            output.put("message", "success");
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> output = new HashMap<>();
            output.put("message", e.getMessage());
            return new ResponseEntity<>(output, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getTransaction/{an}")
    public @ResponseBody ResponseEntity<Object> transferBalance(@PathVariable String an) {
        try {
            return new ResponseEntity<>(webService.getTransaction(an), HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> output = new HashMap<>();
            output.put("message", e.getMessage());
            return new ResponseEntity<>(output, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

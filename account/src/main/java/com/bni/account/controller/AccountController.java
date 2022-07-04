package com.bni.account.controller;

import com.bni.account.dto.EditRequest;
import com.bni.account.dto.TransferRequest;
import com.bni.account.entity.Account;
import com.bni.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    AccountService webService;

    @GetMapping("/getAccounts")
    public @ResponseBody
    ResponseEntity<Object> getAccounts() {
        try {
            return new ResponseEntity<>(webService.getAccounts(), HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> output = new HashMap<>();
            output.put("message", e.getMessage());
            return new ResponseEntity<>(output, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/addAccount")
    public @ResponseBody ResponseEntity<Object> addAccount(@RequestBody @Validated Account account) {
        try {
            webService.addAccount(account);
            Map<String, Object> output = new HashMap<>();
            output.put("message", "success");
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> output = new HashMap<>();
            output.put("message", e.getMessage());
            return new ResponseEntity<>(output, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/editAccount")
    public @ResponseBody ResponseEntity<Object> editAccount(@RequestBody @Validated EditRequest editRequest) {
        try {
            webService.editAccount(editRequest);
            Map<String, Object> output = new HashMap<>();
            output.put("message", "success");
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> output = new HashMap<>();
            output.put("message", e.getMessage());
            return new ResponseEntity<>(output, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAccount/{an}")
    public @ResponseBody ResponseEntity<Object> deleteAccount(@PathVariable String an) {
        try {
            webService.deleteAccount(an);
            Map<String, Object> output = new HashMap<>();
            output.put("message", "success");
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> output = new HashMap<>();
            output.put("message", e.getMessage());
            return new ResponseEntity<>(output, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/updateBalance")
    public @ResponseBody ResponseEntity<Object> updateBalance(@RequestBody @Validated TransferRequest transferRequest) {
        try {
            webService.updateBalance(transferRequest);
            Map<String, Object> output = new HashMap<>();
            output.put("message", "success");
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> output = new HashMap<>();
            output.put("message", e.getMessage());
            return new ResponseEntity<>(output, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

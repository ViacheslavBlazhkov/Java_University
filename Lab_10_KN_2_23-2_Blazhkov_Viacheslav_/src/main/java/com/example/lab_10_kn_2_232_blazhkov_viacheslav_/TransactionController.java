package com.example.lab_10_kn_2_232_blazhkov_viacheslav_;

import com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Exceptions.ClientNotFoundException;
import com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Exceptions.InvalidClientInfoException;
import com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Exceptions.InvalidTypeException;
import com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Models.Transaction;
import com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Models.TransactionDate;
import com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Requests.DepositRequest;
import com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Requests.TransferRequest;
import com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Responses.TransactionResponse;
import com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Responses.TransferResponse;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class TransactionController {
    @GetMapping("/transactions")
    public List<TransactionResponse> allTransactions() {
        var bank = Utils.getBank();
        List<TransactionResponse> responses = new ArrayList<>();
        for (var client : bank.clients) {
            responses.add(new TransactionResponse(
                    client.getId(),
                    client.getTransactions()
            ));
        }
        return responses;
    }

    @GetMapping("/clients/{clientId}/transactions")
    public List<Transaction> clientTransactions(@PathVariable long clientId) {
        var bank = Utils.getBank();
        var client = bank.findClient(clientId);
        if (client == null) {
            throw new ClientNotFoundException(clientId);
        }
        return client.getTransactions();
    }

    @GetMapping("/clients/{clientId}/transactions/{type}")
    public List<Transaction> clientTransactionsByType(@PathVariable long clientId, @PathVariable String type) {
        if (!Objects.equals(type, "deposit") && !Objects.equals(type, "withdraw")) {
            throw new InvalidTypeException();
        }
        var bank = Utils.getBank();
        var client = bank.findClient(clientId);
        if (client == null) {
            throw new ClientNotFoundException(clientId);
        }
        return client.getTransactionsByType(type);
    }

    @PostMapping("/clients/{clientId}/transactions")
    public Transaction depositMoney(@Valid @RequestBody DepositRequest depositRequest, @PathVariable long clientId, BindingResult bindingResult) {
        var bank = Utils.getBank();
        if (bindingResult.hasErrors()) {
            throw new InvalidClientInfoException(bindingResult.getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList()));
        }
        var client = bank.findClient(clientId);
        if (client == null) {
            throw new ClientNotFoundException(clientId);
        }
        return client.depositMoney(depositRequest.getAmount(), new TransactionDate(depositRequest.getDay(), depositRequest.getMonth(), depositRequest.getYear()));
    }

    @PostMapping("/clients/{clientId}/withdraw")
    public Transaction withdrawMoney(@Valid @RequestBody DepositRequest depositRequest, @PathVariable long clientId, BindingResult bindingResult) {
        var bank = Utils.getBank();
        if (bindingResult.hasErrors()) {
            throw new InvalidClientInfoException(bindingResult.getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList()));
        }
        var client = bank.findClient(clientId);
        if (client == null) {
            throw new ClientNotFoundException(clientId);
        }
        return client.withdrawMoney(depositRequest.getAmount(), new TransactionDate(depositRequest.getDay(), depositRequest.getMonth(), depositRequest.getYear()));
    }

    @PostMapping("/clients/transfer")
    public TransferResponse transferMoney(@Valid @RequestBody TransferRequest transferRequest, BindingResult bindingResult) {
        var bank = Utils.getBank();
        if (bindingResult.hasErrors()) {
            throw new InvalidClientInfoException(bindingResult.getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList()));
        }
        var fromClient = bank.findClient(transferRequest.getFromClientId());
        var toClient = bank.findClient(transferRequest.getToClientId());
        if (fromClient == null) {
            throw new ClientNotFoundException(transferRequest.getFromClientId());
        }
        if (toClient == null) {
            throw new ClientNotFoundException(transferRequest.getToClientId());
        }
        var withdraw = fromClient.withdrawMoney(transferRequest.getAmount(), new TransactionDate(transferRequest.getDay(), transferRequest.getMonth(), transferRequest.getYear()));
        var deposit = toClient.depositMoney(transferRequest.getAmount(), new TransactionDate(transferRequest.getDay(), transferRequest.getMonth(), transferRequest.getYear()));
        return new TransferResponse(deposit, withdraw);
    }
}
package web.lab_11_kn_2_232_blazhkov_viacheslav.Controllers.Api;

import domain.Exceptions.ClientNotFoundException;
import domain.Exceptions.InvalidDepositMoneyRequestException;
import domain.Exceptions.InvalidTransferMoneyRequestException;
import domain.Exceptions.NotEnoughMoneyException;
import domain.Models.Transaction;
import domain.Models.TransactionDate;
import domain.Requests.DepositRequest;
import domain.Requests.TransferRequest;
import domain.Responses.TransferResponse;
import domain.Utils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionsApiController {
    @GetMapping("/api/clients/{clientId}/transactions")
    public List<Transaction> transactions(@PathVariable long clientId) {
        var bank = Utils.getBank();
        var client = bank.findClient(clientId);
        if (client == null) {
            throw new ClientNotFoundException(clientId);
        }
        return client.getTransactions();
    }

    @PostMapping("/api/clients/{clientId}/transactions")
    public Transaction depositMoney(@Valid @RequestBody DepositRequest depositRequest, @PathVariable long clientId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDepositMoneyRequestException(bindingResult.getFieldErrors().stream().map(x -> x.getDefaultMessage()).toList());
        }
        var bank = Utils.getBank();
        var client = bank.findClient(clientId);
        if (client == null) {
            throw new ClientNotFoundException(clientId);
        }
        return client.depositMoney(depositRequest.getAmount(), new TransactionDate(depositRequest.getDay(), depositRequest.getMonth(), depositRequest.getYear()));
    }

    @PostMapping("/api/clients/{clientId}/withdraw")
    public ResponseEntity<?> withdrawMoney(@Valid @RequestBody DepositRequest depositRequest, @PathVariable long clientId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDepositMoneyRequestException(bindingResult.getFieldErrors().stream().map(x -> x.getDefaultMessage()).toList());
        }
        var bank = Utils.getBank();
        var client = bank.findClient(clientId);
        if (client == null) {
            throw new ClientNotFoundException(clientId);
        }
        try {
            return ResponseEntity.ok(client.withdrawMoney(
                    depositRequest.getAmount(),
                    new TransactionDate(depositRequest.getDay(), depositRequest.getMonth(), depositRequest.getYear())
            ));
        } catch (NotEnoughMoneyException e) {
            return ResponseEntity.badRequest().body("Недостатньо коштів");
        }
    }

    @PostMapping("/api/clients/transfer")
    public ResponseEntity<?> transferMoney(@Valid @RequestBody TransferRequest transferRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }

        var bank = Utils.getBank();
        var fromClient = bank.findClient(transferRequest.getFromClientId());
        var toClient = bank.findClient(transferRequest.getToClientId());
        
        if (fromClient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Клієнта-відправника не знайдено");
        }
        if (toClient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Клієнта-одержувача не знайдено");
        }
        
        try {
            var withdraw = fromClient.withdrawMoney(
                    transferRequest.getAmount(), 
                    new TransactionDate(transferRequest.getDay(), 
                                        transferRequest.getMonth(), 
                                        transferRequest.getYear())
            );
            var deposit = toClient.depositMoney(
                    transferRequest.getAmount(), 
                    new TransactionDate(transferRequest.getDay(), 
                                        transferRequest.getMonth(), 
                                        transferRequest.getYear())
            );
            return ResponseEntity.ok(new TransferResponse(deposit, withdraw));
        } catch (NotEnoughMoneyException e) {
            return ResponseEntity.badRequest().body("Недостатньо коштів");
        }
    }
}
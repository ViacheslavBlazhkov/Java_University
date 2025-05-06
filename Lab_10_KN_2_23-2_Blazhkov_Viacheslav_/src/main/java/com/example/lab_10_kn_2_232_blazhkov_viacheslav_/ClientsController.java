package com.example.lab_10_kn_2_232_blazhkov_viacheslav_;

import com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Exceptions.ClientNotFoundException;
import com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Exceptions.InvalidClientInfoException;
import com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Models.Client;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClientsController {
    @GetMapping("/clients")
    List<Client> clients() {
        var bank = Utils.getBank();
        return bank.clients;
    }

    @GetMapping("/clients/{clientId}")
    public Client get(@PathVariable long clientId) {
        var bank = Utils.getBank();
        var client = bank.findClient(clientId);
        if (client == null) {
            throw new ClientNotFoundException(clientId);
        }
        return client;
    }

    @PostMapping("/clients")
    public Client newClient(@Valid @RequestBody Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidClientInfoException(bindingResult.getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList()));
        }
        var bank = Utils.getBank();
        return bank.registryClient(client.getName(), client.getEmail());
    }

    @DeleteMapping("/clients/{clientId}")
    public void removeClient(@PathVariable long clientId) {
        var bank = Utils.getBank();
        var client = bank.findClient(clientId);
        bank.removeClient(client);
    }
}

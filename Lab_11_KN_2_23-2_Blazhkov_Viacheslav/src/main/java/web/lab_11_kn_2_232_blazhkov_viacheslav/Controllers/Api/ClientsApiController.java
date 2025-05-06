package web.lab_11_kn_2_232_blazhkov_viacheslav.Controllers.Api;

import domain.Exceptions.ClientNotFoundException;
import domain.Exceptions.InvalidClientInfoException;
import domain.Models.Client;
import domain.Utils;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClientsApiController {
    @GetMapping("/api/clients")
    List<Client> clients() {
        var bank = Utils.getBank();
        return bank.getClients();
    }

    @GetMapping("/api/clients/{clientId}")
    public Client get(@PathVariable long clientId) {
        var bank = Utils.getBank();
        var client = bank.findClient(clientId);
        if(client == null) {
            throw new ClientNotFoundException(clientId);
        }
        return client;
    }

    @PostMapping("/api/clients")
    public Client newClient(@Valid @RequestBody Client client, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new InvalidClientInfoException(bindingResult.getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList()));
        }
        var bank = Utils.getBank();
        return bank.registryClient(client.getName(), client.getEmail());
    }

    @DeleteMapping("/api/clients/{clientId}")
    public void removeClient(@PathVariable long clientId) {
        var bank = Utils.getBank();
        var client = bank.findClient(clientId);
        bank.removeClient(client);
    }
}

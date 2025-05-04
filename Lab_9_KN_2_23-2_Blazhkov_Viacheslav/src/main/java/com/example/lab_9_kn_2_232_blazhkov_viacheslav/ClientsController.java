package com.example.lab_9_kn_2_232_blazhkov_viacheslav;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class ClientsController {
    @GetMapping("/clients")
    public String clients(Model model) {
        var bank = Utils.getBank();
        model.addAttribute("bank", bank);
        return "clients";
    }

    @GetMapping("/client")
    public String getClientForm(@RequestParam(name = "clientId", required = false) long clientId, Model model) {
        var bank = Utils.getBank();
        var client = bank.findUserId(clientId);
        model.addAttribute("client", client);
        return "client";
    }

    @GetMapping("/addclient")
    public String addClientGet(Model model) {
        model.addAttribute("client", new Client());
        return "addclient";
    }

    @PostMapping("/addclient")
    public String addClient(@Valid Client client, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("client", client);
            return "addclient";
        }

        var bank = Utils.getBank();
        bank.registryClient(client.getName(), client.getEmail());
        model.addAttribute("client", client);
        return "redirect:/clients";
    }

    @GetMapping("/chooseuserformoneymovementreport")
    public String chooseuserformoneymovementreportForm(Model model) {
        var bank = Utils.getBank();
        model.addAttribute("clients", bank.clients);
        model.addAttribute("depositrequest", new DepositRequest());
        return "chooseuserformoneymovementreport";
    }

    @GetMapping("/moneymovementreport")
    public String moneymovementreport(@Valid DepositRequest depositRequest, BindingResult bindingResult, Model model) {
        var bank = Utils.getBank();
        long userId = depositRequest.getUserId();
        var user = bank.findUserId(userId);
        var transactions = user.getTransactions();
        model.addAttribute("transactions", transactions);
        return "moneymovementreport";
    }
}

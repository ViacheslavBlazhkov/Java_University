package com.example.lab_9_kn_2_232_blazhkov_viacheslav;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class BankController {
    @GetMapping("/totalamount")
    public String totalamount(Model model) {
        var bank = Utils.getBank();
        model.addAttribute("totalAmount", bank.getTotalAmount());
        return "totalamount";
    }

    @GetMapping("/depositmoney")
    public String depositmoneyForm(Model model) {
        var bank = Utils.getBank();
        model.addAttribute("clients", bank.clients);
        model.addAttribute("depositRequest", new DepositRequest());
        return "depositmoney";
    }

    @PostMapping("/depositmoney")
    public String depositmoney(@Valid DepositRequest depositRequest, BindingResult bindingResult, Model model) {
        var bank = Utils.getBank();

        model.addAttribute("clients", bank.clients);
        if (bindingResult.hasErrors()) {
            model.addAttribute("depositrequest", depositRequest);
            return "depositmoney";
        }

        bank.depositMoney(depositRequest.getUserId(), depositRequest.getAmount(), new TransactionDate(depositRequest.getDay(), depositRequest.getMonth(), depositRequest.getYear()));
        model.addAttribute("bank", bank);
        return "redirect:/clients";
    }

    @GetMapping("/withdrawmoney")
    public String withdrawmoneyForm(Model model) {
        var bank = Utils.getBank();
        model.addAttribute("clients", bank.clients);
        model.addAttribute("depositRequest", new DepositRequest());
        return "withdrawmoney";
    }

    @PostMapping("/withdrawmoney")
    public String withdrawmoney(@Valid DepositRequest depositRequest, BindingResult bindingResult, Model model) throws Exception {
        var bank = Utils.getBank();

        model.addAttribute("clients", bank.clients);
        if (bindingResult.hasErrors()) {
            model.addAttribute("depositrequest", depositRequest);
            return "withdrawmoney";
        }

        try {
            bank.withdrawMoney(depositRequest.getUserId(), depositRequest.getAmount(), new TransactionDate(depositRequest.getDay(), depositRequest.getMonth(), depositRequest.getYear()));
        } catch (Exception e) {
            return "infucientcosts";
        }
        model.addAttribute("bank", bank);
        return "redirect:/clients";
    }

    @GetMapping("/transfermoney")
    public String transfermoneyForm(Model model) {
        var bank = Utils.getBank();
        model.addAttribute("clients", bank.clients);
        model.addAttribute("transferRequest", new TransferRequest());
        return "transfermoney";
    }

    @PostMapping("/transfermoney")
    public String transfermoney(@Valid TransferRequest transferRequest, BindingResult bindingResult, Model model) throws Exception {
        var bank = Utils.getBank();

        model.addAttribute("clients", bank.clients);
        if (bindingResult.hasErrors()) {
            model.addAttribute("transferRequest", transferRequest);
            return "withdrawmoney";
        }

        if (transferRequest.getFromUserId() == transferRequest.getToUserId()) {
            return "sameuser";
        }

        try {
            bank.transferMoney(transferRequest.getFromUserId(), transferRequest.getToUserId(), transferRequest.getAmount(), new TransactionDate(transferRequest.getDay(), transferRequest.getMonth(), transferRequest.getYear()));
        } catch (Exception e) {
            return "infucientcosts";
        }
        model.addAttribute("bank", bank);
        return "redirect:/clients";
    }

    @GetMapping("/choosetypefortransactionreport")
    public String choosetypefortransactionreportForm(Model model) {
        var bank = Utils.getBank();
        model.addAttribute("clients", bank.clients);
        model.addAttribute("transactionReportRequest", new TransactionReportRequest());
        return "choosetypefortransactionreport";
    }

    @GetMapping("/transactionreport")
    public String transactionreport(@Valid TransactionReportRequest request, BindingResult bindingResult, Model model) throws Exception {
        var bank = Utils.getBank();

        model.addAttribute("clients", bank.clients);
        if (bindingResult.hasErrors()) {
            model.addAttribute("transactionReportRequest", request);
            return "choosetypefortransactionreport";
        }
        long userId = request.getUserId();
        var user = bank.findUserId(userId);
        var transactions = user.getTransactionsByType(request.getType());
        model.addAttribute("transactions", transactions);
        model.addAttribute("client", user);
        model.addAttribute("bank", bank);
        return "transactionreport";
    }
}

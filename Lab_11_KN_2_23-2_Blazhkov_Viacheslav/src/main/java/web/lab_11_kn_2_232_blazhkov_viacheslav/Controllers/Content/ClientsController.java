package web.lab_11_kn_2_232_blazhkov_viacheslav.Controllers.Content;

import domain.Requests.DepositRequest;
import domain.Requests.TransferRequest;
import domain.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientsController {
    @GetMapping("/clients")
    public String clients() {
        return "clients";
    }

    @GetMapping("/client")
    public String clients(@RequestParam(name = "clientId", required = false) long clientId, Model model) {
        model.addAttribute("clientId", clientId);
        return "client";
    }

    @GetMapping("/addclient")
    public String addClient() {
        return "addclient";
    }

    @GetMapping("/chooseuserformoneymovementreport")
    public String chooseuserformoneymovementreportForm(Model model) {
        var bank = Utils.getBank();
        model.addAttribute("clients", bank.clients);
        model.addAttribute("depositRequest", new DepositRequest());
        return "chooseuserformoneymovementreport";
    }

    @GetMapping("/deposit")
    public String depositForm(Model model) {
        var bank = Utils.getBank();
        model.addAttribute("clients", bank.clients);
        model.addAttribute("depositRequest", new DepositRequest());
        return "deposit";
    }

    @GetMapping("/withdraw")
    public String withdrawForm(Model model) {
        var bank = Utils.getBank();
        model.addAttribute("clients", bank.clients);
        model.addAttribute("depositRequest", new DepositRequest());
        return "withdraw";
    }

    @GetMapping("/transfer")
    public String transferForm(Model model) {
        var bank = Utils.getBank();
        model.addAttribute("clients", bank.clients);
        model.addAttribute("transferRequest", new TransferRequest());
        return "transfer";
    }

    @GetMapping("/unregister")
    public String unregisterForm(Model model) {
        var bank = Utils.getBank();
        model.addAttribute("clients", bank.clients);
        return "unregister";
    }
}

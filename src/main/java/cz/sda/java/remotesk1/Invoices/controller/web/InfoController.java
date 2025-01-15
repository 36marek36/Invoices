package cz.sda.java.remotesk1.Invoices.controller.web;

import cz.sda.java.remotesk1.Invoices.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InfoController {

    private final InfoService infoService;

    @Autowired
    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/")
    String info(Model model){
        setDefaultValues(model);
        model.addAttribute("info", infoService.getInfo());
        return "info";
    }

    private void setDefaultValues(Model model){
        model.addAttribute("pageTitle", "Infoteria");
    }
}

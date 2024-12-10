package cz.sda.java.remotesk1.Invoices.controller.web;

import cz.sda.java.remotesk1.Invoices.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {

    private final InfoService infoService;

    @Autowired
    public InfoController(@Qualifier("infoService") InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/info")
    String info(Model model){
        model.addAttribute("info",infoService.getInfo());
        return "info";
    }
}

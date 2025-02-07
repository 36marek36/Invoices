package cz.sda.java.remotesk1.Invoices.controller.rest;

import cz.sda.java.remotesk1.Invoices.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class InfoApi {

    private final InfoService infoService;

    @Autowired
    public InfoApi(@Qualifier("infoService") InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/info")
    String info(){
        return infoService.getInfo();
    }
}

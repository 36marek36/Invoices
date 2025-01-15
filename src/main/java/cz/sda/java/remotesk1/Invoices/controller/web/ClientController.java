package cz.sda.java.remotesk1.Invoices.controller.web;

import cz.sda.java.remotesk1.Invoices.controller.web.request.CreateClient;
import cz.sda.java.remotesk1.Invoices.controller.web.request.UpdateClient;
import cz.sda.java.remotesk1.Invoices.model.Client;
import cz.sda.java.remotesk1.Invoices.service.ClientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    String getAllClients(Model model) {
        setDefaultValues(model);
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("createClient",new Client());
        return "clients";
    }

//    @PostMapping("/add")
//    String addUser(Client client, Model model) {
//        clientService.addClient(client.name(),client.address());
//        return "redirect:/clients/";
//    }

    @PostMapping("/add")
    String addUser(@Valid CreateClient client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("createClient", client);
            model.addAttribute("clients", clientService.getAllClients());
            return "clients";
        }
        setDefaultValues(model);
        clientService.addClient(client.getName(),client.getAddress());
        return "redirect:/clients/";
    }

    @GetMapping("/delete/{id}")
    String deleteUser(@PathVariable String id, Model model) {
        setDefaultValues(model);
        clientService.removeClient(id);
        return "redirect:/clients/";
    }

    @GetMapping("/edit/{id}")
    String updateUser(@PathVariable String id, Model model) {
        setDefaultValues(model);
        model.addAttribute("updateClient", clientService.getClient(id));
        return "edit-client";
    }

    @PostMapping("/update/{id}")
    String updateUser(@PathVariable String id, @Valid UpdateClient client, BindingResult result, Model model) {
        setDefaultValues(model);
        if (result.hasErrors()) {
            client.setId(id);
            model.addAttribute("updateClient", client);
            return "edit-client";
        }
        clientService.updateClient(client.getId(),new Client(client.getId(),client.getName(),client.getAddress()));
        return "redirect:/clients/";
    }

    private void setDefaultValues (Model model) {
        model.addAttribute("pageTitle", "Clienteria");
    }

}

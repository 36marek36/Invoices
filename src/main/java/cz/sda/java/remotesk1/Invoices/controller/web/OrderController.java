package cz.sda.java.remotesk1.Invoices.controller.web;

import cz.sda.java.remotesk1.Invoices.controller.web.request.CreateOrder;
import cz.sda.java.remotesk1.Invoices.controller.web.request.UpdateOrder;
import cz.sda.java.remotesk1.Invoices.model.Order;
import cz.sda.java.remotesk1.Invoices.service.ClientService;
import cz.sda.java.remotesk1.Invoices.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
class OrderController {

    private final OrderService orderService;
    private final ClientService clientService;

    @Autowired
    OrderController(OrderService orderService, ClientService clientService) {
        this.orderService = orderService;
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        model.addAttribute("createOrder", new CreateOrder());
        model.addAttribute("clientList", clientService.getAllClients());
        return "order-list";
    }

    @GetMapping("/edit/{id}")
    public String getOrderById(@PathVariable("id") String id, Model model) {
        var order = orderService.getOrder(id);
        model.addAttribute("updateOrder", new UpdateOrder(order.getId(), order.getClient().getId(), order.getDate()));
        model.addAttribute("clientList", clientService.getAllClients());
        return "order-edit";
    }

    @PostMapping("/add")
    public String addOrder(CreateOrder order) {
        orderService.addOrder(order.getClientId(), order.getDate());
        return "redirect:/orders/";
    }

    @PostMapping("/update")
    public String updateOrder(UpdateOrder order, Model model) {
        orderService.updateOrder(order.orderId(), order.clientId(), order.date());
        return "redirect:/orders/";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") String id, Model model) {
        orderService.removeOrder(id);
        return "redirect:/orders/";
    }
}
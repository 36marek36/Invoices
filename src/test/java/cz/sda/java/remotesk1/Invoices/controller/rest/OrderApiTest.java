//package cz.sda.java.remotesk1.Invoices.controller.rest;
//
//import cz.sda.java.remotesk1.Invoices.model.Client;
//import cz.sda.java.remotesk1.Invoices.model.Order;
//import cz.sda.java.remotesk1.Invoices.service.ClientService;
//import cz.sda.java.remotesk1.Invoices.service.OrderService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.time.LocalDate;
//import java.util.UUID;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//
//@WebMvcTest(OrderApi.class)
//public class OrderApiTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockitoBean
//    private OrderService orderService;
//
//    @MockitoBean
//    private ClientService clientService;
//
//    @Test
//    void addOrderShouldReturnCreatedOrder() throws Exception {
//        var orderId = UUID.randomUUID().toString();
//        var order = new Order(orderId,clientService.getClient(UUID.randomUUID().toString()),LocalDate.now());
//        Mockito.when(orderService.addOrder(Mockito.any(String.class), Mockito.any(LocalDate.class))).thenReturn(order);
//
//    }
//}

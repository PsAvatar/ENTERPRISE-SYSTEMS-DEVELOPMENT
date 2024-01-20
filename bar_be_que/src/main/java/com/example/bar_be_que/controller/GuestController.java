package com.example.bar_be_que.controller;

import com.example.bar_be_que.dto.NewOrderDto;
import com.example.bar_be_que.model.Burger;
import com.example.bar_be_que.model.Order;
import com.example.bar_be_que.repository.BurgerRepository;
import com.example.bar_be_que.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class GuestController {

    @Autowired
    private BurgerRepository burgerRepository;
    @Autowired
    private OrderRepository orderRepository;


    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/guest/order")
    public String guestOrder(Model model) {
        NewOrderDto orderDto = new NewOrderDto();
        model.addAttribute("orderDto", orderDto);
        List<Burger> burgers = burgerRepository.findAll();
        model.addAttribute("burgers", burgers);
        return "guest_order";
    }

    @PostMapping("/guest/order")
    public String postGuestOrder(@ModelAttribute NewOrderDto orderDto, Model model) {
        // model.addAttribute("orderDto", orderDto);
        Optional<Burger> optional = burgerRepository.findById(orderDto.getBurgerId());
        Burger burger = optional.get();
        Order order = new Order();
        order.setGuest(orderDto.getGuest());
        order.setBurger(burger);
        order.setCompleted(false);
        order.setDate_set(new Date());
        orderRepository.save(order);
        orderRepository.flush();
        model.addAttribute("order", order);
        return "order_no";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
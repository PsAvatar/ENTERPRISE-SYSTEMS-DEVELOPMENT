package com.example.bar_be_que.controller;

import com.example.bar_be_que.dto.CompleteOrderDto;
import com.example.bar_be_que.model.Order;
import com.example.bar_be_que.model.User;
import com.example.bar_be_que.repository.OrderRepository;
import com.example.bar_be_que.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class StaffController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/staff/dashboard")
    public String staff_dashboard() {
        return "staff_dashboard";
    }

    @GetMapping("/staff/orders")
    public String staff_orders(Model model) {
        List<Order> orders = orderRepository.findByCompleted(false);
        model.addAttribute("orders", orders);
        CompleteOrderDto orderDto = new CompleteOrderDto();
        model.addAttribute("orderDto", orderDto);
        return "staff_orders";
    }

    @PostMapping("/staff/complete/order")
    public String staffCompleteOrder(@ModelAttribute CompleteOrderDto orderDto, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(auth.getName());
        Optional<Order> optional = orderRepository.findById(orderDto.getOrderId());
        if(optional.isPresent()) {
            Order order = optional.get();
            order.setCompleted(true);
            order.setStaff(user);
            orderRepository.save(order);
            return "redirect:/staff/orders";
        }
        else {
            model.addAttribute("error_msg", "Id " + orderDto.getOrderId() + " not found");
            return "error";
        }
    }
}
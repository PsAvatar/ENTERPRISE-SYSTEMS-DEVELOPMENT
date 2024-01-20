package com.example.bar_be_que.controller;

import com.example.bar_be_que.model.Burger;
import com.example.bar_be_que.model.User;
import com.example.bar_be_que.repository.BurgerRepository;
import com.example.bar_be_que.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BurgerRepository burgerRepository;

    @GetMapping("/manager/dashboard")
    public String manager_dashboard() {
        return "manager_dashboard";
    }

    @GetMapping("/manager/orders/by/staff")
    public String oders_by_staff(Model model) {
        List<User> staff = userRepository.findAllByRole("ROLE_STAFF");
        model.addAttribute("staff", staff);
        return "oders_by_staff";
    }

    @GetMapping("/manager/burgers")
    public String burgers(Model model) {
        List<Burger> burgers = burgerRepository.findAll();
        model.addAttribute("burgers", burgers);

        return "manager_burgers";
    }
}
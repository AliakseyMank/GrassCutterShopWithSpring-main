package com.alexey.GrassCutterShopWithSpring.controller;

import com.alexey.GrassCutterShopWithSpring.model.GrassCutter;
import com.alexey.GrassCutterShopWithSpring.model.Order;
import com.alexey.GrassCutterShopWithSpring.model.User;
import com.alexey.GrassCutterShopWithSpring.repo.GrassCutterRepository;
import com.alexey.GrassCutterShopWithSpring.repo.OrderRepository;
import com.alexey.GrassCutterShopWithSpring.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {
    private final UserRepository userRepository;
    private final GrassCutterRepository grassCutterRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(UserRepository userRepository, GrassCutterRepository grassCutterRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.grassCutterRepository = grassCutterRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/order/myOrders")
    public String myOrders(Model model, Principal principal){
        String userName = principal.getName();
        Optional <User> userByOptional = userRepository.findByEmail(userName);
        User user = userByOptional.get();
        long user_id = user.getId();
        List<Order> orderList = orderRepository.findByUserId(user_id);
        int count = orderList.size();
        model.addAttribute("count", count);
        model.addAttribute("order", orderList);
        return "orders-my";
    }

    @GetMapping("/order/all")
    public String allOrders(Model model){
        List<Order> orderList =  new ArrayList<>(orderRepository.findAll());
        int count = orderList.size();
        model.addAttribute("order", orderList);
        model.addAttribute("count", count);
        return "orders";
    }

    @PostMapping("/order/add/{id}")
    public String addOrder(@PathVariable(value = "id") long id, Principal principal){
        String name = principal.getName();
        Optional<User> user = userRepository.findByEmail(name);
        User user1 = user.get();
        Optional <GrassCutter> grassCutter = grassCutterRepository.findById(id);
        GrassCutter grassCutter1 = grassCutter.get();
        Order newOrder = new Order(user1, grassCutter1);
        orderRepository.save(newOrder);
        return "redirect:/grassCutters";
    }
}

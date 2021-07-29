package com.example.rmmservicesserverapp.controller;

import com.example.rmmservicesserverapp.dto.DeviceDTO;
import com.example.rmmservicesserverapp.model.User;
import com.example.rmmservicesserverapp.model.UserInfo;
import com.example.rmmservicesserverapp.repos.ServiceRepository;
import com.example.rmmservicesserverapp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServiceRepository serviceRepository;

    //// Login methods

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login";
    }

    @GetMapping("/onLoggedIn")
    public String onLoggedIn(Model model) {
        model.addAttribute("deviceTypes", serviceRepository.findDistinctDeviceType());
        model.addAttribute("services", serviceRepository.findDistinctServices());
        User user = ((UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        model.addAttribute("user", user);
        return "userHome";
    }

    @PostMapping("/processLogin")
    public String processLogin(@RequestParam("username") String userName, @RequestParam("password") String password, Model model) {
        return "login";
    }

    @GetMapping("/services")
    public String getServices(Model model) {
        model.addAttribute("user", ((UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser());
        model.addAttribute("services", serviceRepository.findDistinctServices());
        return "services";
    }

    @GetMapping("/getUserByName/{username}")
    @ResponseBody
    public User getUserByName(@PathVariable("username") String userName) {
        return userRepository.findOneByUserName(userName);
    }

}

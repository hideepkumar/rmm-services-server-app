package com.example.rmmservicesserverapp.controller;

import com.example.rmmservicesserverapp.dto.ServiceDTO;
import com.example.rmmservicesserverapp.model.*;
import com.example.rmmservicesserverapp.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RMMServiceController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServiceRepository userServiceRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @ResponseBody
    @RequestMapping(value = "/users/{userName}/services", headers = {
            "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public void addServiceToUser(@PathVariable("userName") String userName, @RequestBody ServiceDTO service) {
        User user = userRepository.findOneByUserName(userName);
        for(String sName: service.getServiceName()) {
            user.addService(new UserService(new UserServiceID(userName, sName)));
        }
        userRepository.save(user);
    }

    @ResponseBody
    @RequestMapping(value = "/users/{userName}/services/{serviceName}", headers = {
            "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public void addServiceToUser(@PathVariable("userName") String userName, @PathVariable("serviceName") String serviceName) {
        User user = userRepository.findOneByUserName(userName);
        user.addService(new UserService(new UserServiceID(userName, serviceName)));
        userRepository.save(user);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteService/{userName}/{serviceName}", headers = {
            "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public void deleteServiceToUser(@PathVariable("userName") String userName, @PathVariable("serviceName") String serviceName) {
        User user = userRepository.findOneByUserName(userName);
        user.getServices().remove(new UserService(new UserServiceID(userName, serviceName)));
        userRepository.save(user);
    }

    @RequestMapping(value = "/monthlyBill/{userName}", method = RequestMethod.GET)
    public String getUsersMonthlyBill(@PathVariable("userName") String userName) {
        double bill = 0.0;
        Map<String, Double> serviceChargeMap = new HashMap<>();
        List<Service> services = serviceRepository.findAll();
        services.forEach(service ->
                serviceChargeMap.put(service.getServiceName() + "_" + service.getDeviceType(), service.getCost()));
        User user = userRepository.findOneByUserName(userName);
        if (user != null && !user.getDevices().isEmpty()) {
            bill = 4 * user.getDevices().size();
            for (UserService userService: user.getServices()) {
                for (Device device: user.getDevices()) {
                    bill = bill + serviceChargeMap.get(userService.getServiceID().getServiceName() + "_" + device.getDeviceType());
                }
            }
        }
        return String.valueOf(bill);
    }

}

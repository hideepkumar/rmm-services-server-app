package com.example.rmmservicesserverapp.controller;

import com.example.rmmservicesserverapp.dto.DeviceDTO;
import com.example.rmmservicesserverapp.model.Device;
import com.example.rmmservicesserverapp.model.User;
import com.example.rmmservicesserverapp.repos.DeviceRepository;
import com.example.rmmservicesserverapp.repos.ServiceRepository;
import com.example.rmmservicesserverapp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class RMMDeviceController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @ResponseBody
    @RequestMapping(value = "/users/{userName}/devices", headers = {
            "content-type=application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public Device addDeviceToUser(@PathVariable("userName") String userName, @RequestBody DeviceDTO deviceDTO) {
        User user = userRepository.findOneByUserName(userName);
        Device device = new Device(deviceDTO.getDeviceId(), deviceDTO.getSystemName(), deviceDTO.getDeviceType());
        user.addDevice(device);
        userRepository.save(user);
        return device;
    }

    @RequestMapping(value = "/devices/{userName}", method = RequestMethod.GET)
    public List<Device> getUserDevices(@PathVariable("userName") String userName) {
        User user = userRepository.findOneByUserName(userName);
        return deviceRepository.findByUser(user);
    }

    @RequestMapping(value = "/device/{deviceId}", method = RequestMethod.GET)
    public Device getDevice(@PathVariable("deviceId") String deviceId) {
        Device device = deviceRepository.findOneByDeviceId(deviceId);
        return device;
    }

    @RequestMapping(value = "/deleteDevice/{userName}/{deviceId}", method = RequestMethod.POST)
    public void deleteDevice(@PathVariable String userName, @PathVariable String deviceId) {
        User user = userRepository.findOneByUserName(userName);
        if (user != null && !user.getDevices().isEmpty()) {
            Set<Device> devices = user.getDevices();
            Device device = deviceRepository.findOneByDeviceId(deviceId);
            if (device != null) {
                devices.remove(device);
                user.setDevices(devices);
                userRepository.save(user);
            }
        }
    }
}

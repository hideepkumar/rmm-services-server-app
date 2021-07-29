package com.example.rmmservicesserverapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    @OneToMany(mappedBy="user", cascade= CascadeType.ALL, orphanRemoval = true)
    private Set<Device> devices = new HashSet<>();

    @OneToMany(mappedBy="user", cascade= CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<UserService> services = new HashSet<>();

    public User() {}

    public User(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    public void addDevice(Device device) {
        device.setUser(this);
        this.devices.add(device);
    }

    public Set<UserService> getServices() {
        return services;
    }

    public void setServices(Set<UserService> services) {
        this.services = services;
    }

    public void addService(UserService service) {
        service.setUser(this);
        this.services.add(service);
    }
}

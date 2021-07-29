package com.example.rmmservicesserverapp.model;

import javax.persistence.*;

@Entity
public class Service {
    @Id
    @GeneratedValue
    private Long id;
    private String serviceName;
    private String deviceType;
    private Double cost;

//    @ManyToOne
//    @JoinColumn(name="user_id", nullable=false)
//    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}

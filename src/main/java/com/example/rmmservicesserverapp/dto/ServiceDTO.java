package com.example.rmmservicesserverapp.dto;

public class ServiceDTO {

    private String []serviceName;

    public ServiceDTO(){}

    public ServiceDTO(String []serviceName) {
        this.serviceName = serviceName;
    }

    public String[] getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName[]) {
        this.serviceName = serviceName;
    }
}

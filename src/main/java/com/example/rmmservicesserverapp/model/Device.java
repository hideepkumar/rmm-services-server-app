package com.example.rmmservicesserverapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Device {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String deviceId;
    private String systemName;
    private String deviceType;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @JsonIgnore
    private User user;

    public Device(){}

    public Device(String deviceId, String systemName, String deviceType) {
        this.deviceId = deviceId;
        this.systemName = systemName;
        this.deviceType = deviceType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device that = (Device) o;

        return deviceId.equals(that.deviceId);
    }

    @Override
    public int hashCode() {
        int result = deviceId.hashCode();
        result = 31 * result + deviceId.hashCode();
        return result;
    }
}

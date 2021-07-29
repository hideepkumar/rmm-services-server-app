package com.example.rmmservicesserverapp.dto;

public class DeviceDTO {

    private String deviceId;
    private String systemName;
    private String deviceType;

    public DeviceDTO(){}

    public DeviceDTO(String deviceId, String systemName, String deviceType) {
        this.deviceId = deviceId;
        this.systemName = systemName;
        this.deviceType = deviceType;
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
}

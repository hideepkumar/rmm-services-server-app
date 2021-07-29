package com.example.rmmservicesserverapp.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserServiceID implements Serializable {
    private String userName;
    private String serviceName;

    public UserServiceID(){}

    public UserServiceID(String userName, String serviceName) {
        this.userName = userName;
        this.serviceName = serviceName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserServiceID that = (UserServiceID) o;

        if (!userName.equals(that.userName)) return false;
        return serviceName.equals(that.serviceName);
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + serviceName.hashCode();
        return result;
    }
}

package com.example.rmmservicesserverapp.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserService {

    @EmbeddedId
    private UserServiceID serviceID;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public UserService(){}

    public UserService(UserServiceID serviceID) {
        this.serviceID = serviceID;
    }

    public UserServiceID getServiceID() {
        return serviceID;
    }

    public void setServiceID(UserServiceID serviceID) {
        this.serviceID = serviceID;
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

        UserService that = (UserService) o;

        if (!serviceID.equals(that.serviceID)) return false;
        return serviceID.equals(that.serviceID);
    }

    @Override
    public int hashCode() {
        int result = serviceID.hashCode();
        result = 31 * result + serviceID.hashCode();
        return result;
    }
}

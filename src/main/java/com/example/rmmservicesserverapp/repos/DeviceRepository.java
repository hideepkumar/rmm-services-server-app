package com.example.rmmservicesserverapp.repos;

import com.example.rmmservicesserverapp.model.Device;
import com.example.rmmservicesserverapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByUser(User user);
    Device findOneByDeviceId(String deviceId);
}

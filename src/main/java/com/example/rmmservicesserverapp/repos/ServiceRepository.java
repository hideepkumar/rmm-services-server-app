package com.example.rmmservicesserverapp.repos;

import com.example.rmmservicesserverapp.model.Service;
import com.example.rmmservicesserverapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    @Query("SELECT DISTINCT a.deviceType FROM Service a")
    List<String> findDistinctDeviceType();

    @Query("SELECT DISTINCT a.serviceName FROM Service a")
    List<String> findDistinctServices();
}

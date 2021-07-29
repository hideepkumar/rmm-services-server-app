package com.example.rmmservicesserverapp.repos;

import com.example.rmmservicesserverapp.model.User;
import com.example.rmmservicesserverapp.model.UserService;
import com.example.rmmservicesserverapp.model.UserServiceID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.security.jca.ServiceId;

@Repository
public interface UserServiceRepository extends JpaRepository<UserService, UserServiceID> {

    @Transactional
    void deleteByServiceID(UserServiceID userServiceID);

    UserService findOneByServiceID(UserServiceID userServiceID);

//    @Transactional
//    void deleteByServiceID(UserServiceID userService);
//
//    UserServiceID findOneByServiceID(UserServiceID userServiceID);
}

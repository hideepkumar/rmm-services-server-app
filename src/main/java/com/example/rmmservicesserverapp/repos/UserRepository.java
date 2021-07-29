package com.example.rmmservicesserverapp.repos;

import com.example.rmmservicesserverapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User findOneByUserName(String userName);
}

package com.mars.ums.jpa.repository.auth;

import com.mars.ums.jpa.bean.auth.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository extends JpaRepository<User,Long>{

    @EntityGraph(value="userdemo")
    User findByUsername(String username);

    @EntityGraph(value="userdemo")
    List<User> findAll();

}

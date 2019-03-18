package com.sanshao.shiro.repository.auth;

import com.sanshao.shiro.bean.auth.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @EntityGraph(value = "roledemo")
    public Role findByRolename(String rolename);

    @EntityGraph(value = "roledemo")
    public List<Role> findAll();
}

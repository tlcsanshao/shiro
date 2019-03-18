package com.sanshao.shiro.config;


import com.sanshao.shiro.bean.auth.Permission;
import com.sanshao.shiro.bean.auth.Role;
import com.sanshao.shiro.bean.auth.User;
import com.sanshao.shiro.repository.auth.PermissionRepository;
import com.sanshao.shiro.repository.auth.RoleRepository;
import com.sanshao.shiro.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class InitDb implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PermissionRepository permissionRepository;


    @Override
    public void run(String... args) throws Exception {
        initPermission();
        initUser();
    }

    public void initPermission() throws Exception {

        Permission permission = new Permission();
        permission.setPermissionname("Add Node");
        permissionRepository.save(permission);

        permission = new Permission();
        permission.setPermissionname("Delete Node");
        permissionRepository.save(permission);

    }



    public void initUser() throws Exception {

        Role role = new Role();
        role.setRolename("Admin");

        Set<Permission> permissions = new HashSet<>();
        Permission permission1 = permissionRepository.findByPermissionname("Add Node");
        Permission permission2 = permissionRepository.findByPermissionname("Delete Node");;
        permissions.add(permission1);
        permissions.add(permission2);
        role.setPermissions(permissions);
        roleRepository.save(role);

        Set<Role> roles = new HashSet<Role>();
        roles.add(role);

        User user = new User();
        user.setUsername("root");
        user.setPassword(Util.getEncryptedPassword("root","123456"));
        user.setRoles(roles);
        userRepository.save(user);

    }


}

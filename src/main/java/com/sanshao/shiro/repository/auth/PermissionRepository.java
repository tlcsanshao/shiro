package com.sanshao.shiro.repository.auth;

import com.sanshao.shiro.bean.auth.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Long>{
    public Permission findByPermissionname(String permissionname);
}

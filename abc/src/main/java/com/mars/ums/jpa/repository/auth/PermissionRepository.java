package com.mars.ums.jpa.repository.auth;

import com.mars.ums.jpa.bean.auth.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Long>{
    public Permission findByPermissionname(String permissionname);
}

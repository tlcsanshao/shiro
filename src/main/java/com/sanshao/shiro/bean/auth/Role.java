package com.sanshao.shiro.bean.auth;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedEntityGraph(name = "roledemo", attributeNodes = {
        @NamedAttributeNode("permissions")
})
@Table(name="marsrole")
public class Role {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String rolename;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonBackReference
    //@JsonBackReference防止无限序列化。该属性在对象转为json数据时，会被忽略
    private Set<User> users = new HashSet<User>();

    @ManyToMany(fetch = FetchType.LAZY)
//    @JsonBackReference
    @JoinTable(name = "auth_role_permisson", joinColumns = {@JoinColumn(name = "r_id")},
            inverseJoinColumns = {@JoinColumn(name = "p_id")})
    private Set<Permission> permissions = new HashSet<Permission>();

    private String moids;

    public String getMoids() {
        return moids;
    }

    public void setMoids(String moids) {
        this.moids = moids;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void addPermission(Permission permission) {
        permissions.add(permission);
    }

    public void removePermission(Permission permission) {
        permissions.remove(permission);
    }


    public boolean hasPermission(Permission permission) {
        if (permissions.contains(permission)) {
            return true;
        }
        return false;
    }

}

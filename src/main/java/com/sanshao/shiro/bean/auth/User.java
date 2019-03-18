package com.sanshao.shiro.bean.auth;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedEntityGraph(name = "userdemo", attributeNodes = {
        @NamedAttributeNode("roles")
})
@Table(name="marsuser")
//@NamedEntityGraph减少查询语句。
// 后续深入优化时使用。非常重要。
//1.在Bean对象处创建关系图，填入关系图名称和想要优化的属性。
//2.在repository处的相应方法前，添加@EntityGraph注解。则该方法所对应的查询会优化相关属性的获取。
//3.一般会将N条语句缩减成一条。目前还没有发现缺点。
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    private int locked = 0;
    @Column(name = "starttime")
    private long starttime = 0l;
    @Column(name = "endtime")
    private long endtime = 0l;

    private int online = 1;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "auth_user_role", joinColumns = {@JoinColumn(name = "u_id")},
            inverseJoinColumns = {@JoinColumn(name = "r_id")})
    private Set<Role> roles = new HashSet<Role>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public long getStarttime() {
        return starttime;
    }

    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    public long getEndtime() {
        return endtime;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public void setEndtime(long endtime) {
        this.endtime = endtime;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", locked=" + locked +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", online=" + online +
                ", roles=" + roles +
                '}';
    }
}

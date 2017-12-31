package com.fabio.springmvc.domain.security;

import com.fabio.springmvc.domain.AbstractDomainClass;
import com.fabio.springmvc.domain.User;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role extends AbstractDomainClass{

    private String role;
    @ManyToMany
    @JoinTable
    //  ~~ @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumns(name = "role_id")
    // , inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();

    public void addUser(User user){
        if(!this.users.contains(user)){
            this.users.add(user);
        }
        if(!user.getRoles().contains(this)){
            user.getRoles().add(this);
        }
    }

    public void removeUser(User user){
        this.users.remove(user);
        user.getRoles().remove(this);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

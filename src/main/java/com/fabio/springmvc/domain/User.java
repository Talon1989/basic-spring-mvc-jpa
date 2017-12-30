package com.fabio.springmvc.domain;

import javax.persistence.*;

@Entity
public class User extends AbstractDomainClass implements DomainObject {

    private String username;
    @Transient // not gonna persist in the database
    private String password;
    private String encryptedPassword;
    private Boolean enabled = true;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}) // if creates one doesn't automatically one customer
    private Customer customer;
    @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true) // del all automatically
    private Cart cart;

    public User() {
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

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customer.setUser(this);
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}

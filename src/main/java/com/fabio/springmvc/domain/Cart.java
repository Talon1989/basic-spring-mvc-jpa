package com.fabio.springmvc.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart extends AbstractDomainClass{

    @OneToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart"
            , orphanRemoval = true)
    private List<CartDetail> cartDetails = new ArrayList<>();


    public void addCartDetail(CartDetail cd){
        this.cartDetails.add(cd);
        cd.setCart(this);
    }

    public void removeCartDetail(CartDetail cd){
        cd.setCart(null);
        this.cartDetails.remove(cd);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartDetail> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }

}

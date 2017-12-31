package com.fabio.springmvc.domain;

import com.fabio.springmvc.domain.enums.OrderStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDER_HEADER")
public class Order extends AbstractDomainClass {

    @OneToOne // unidirectional
    private Customer customer;
    @Embedded
    private Address shipToAddress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    List<OrderDetail> orderDetails = new ArrayList<>();
    private OrderStatus orderStatus;
    private Date dateShipped;

    public void addToOrderDetails(OrderDetail orderDetail){
        orderDetail.setOrder(this);
        orderDetails.add(orderDetail);
    }

    public void removeOrderDetail(OrderDetail orderDetail){
        orderDetail.setOrder(null);
        orderDetails.remove(orderDetail);
    }

    public Order() {
        dateShipped = new Date();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getShipToAddress() {
        return shipToAddress;
    }

    public void setShipToAddress(Address shipToAddress) {
        this.shipToAddress = shipToAddress;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(Date dateShipped) {
        this.dateShipped = dateShipped;
    }
}

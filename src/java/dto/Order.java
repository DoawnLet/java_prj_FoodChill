/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;
import java.util.List;
import mylib.Shipper;
import mylib.Status;

/**
 *
 * @author Asus
 */
public class Order {

    private int orderId;
    private int user_ID;
    private String name;
    private String email;
    private String phone;
    private Status status;
    private Date orderDate;
    private Shipper shipper;
    private String address;
    
    private double total;
    private List<OrderDetail> details;

    public Order() {
    }

    public Order(int orderId, int user_ID, String name,String email, String phone, Status status, Date orderDate, Shipper shipper, String address, double total, List<OrderDetail> details) {
        this.orderId = orderId;
        this.user_ID = user_ID;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.orderDate = orderDate;
        this.shipper = shipper;
        this.address = address;
        this.total = total;
        this.details = details;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
   
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }
    
    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", user_ID=" + user_ID + ", name=" + name + ", phone=" + phone + ", status=" + status + ", orderDate=" + orderDate + ", shipper=" + shipper + ", address=" + address + ", total=" + total + ", details=" + details + '}';
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Asus
 */
public class OrderDetail {

    private Order order;
    private Food food;
    private double unitPrice;
    private int quantity;
    private int discount;

    public OrderDetail(Order order, Food food, double unitPrice, int quantity) {
        this.order = order;
        this.food = food;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public OrderDetail(Food food, double unitPrice, int quantity) {
        this.food = food;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public OrderDetail(Food food, double unitPrice, int quantity, int discount) {
        this.food = food;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }
    
    
    
    
    public OrderDetail(Order order, Food food, double unitPrice, int quantity, int discount) {
        this.order = order;
        this.food = food;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "order=" + order + ", food=" + food + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", discount=" + discount + '}';
    }

}

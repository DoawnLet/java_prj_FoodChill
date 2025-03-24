/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class Food implements Serializable{
       private static final long serialVersionUID = 1L; // Required for serialization
    
    
    private String id;
    private String cateId;
    private String name;
    private double price;
    private String desc;
    private String img;
    
    public Food() {
    }

    public Food(String id, String cateId, String name, double price, String desc, String img) {
        this.id = id;
        this.cateId = cateId;
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.img = img;
    }

    
    
    public Food(String id, String name, double price, String desc, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }
    
    

    @Override
    public String toString() {
        return "Food{" + "id=" + id + ", name=" + name + ", price=" + price + ", desc=" + desc + ", img=" + img + '}';
    }
    
    
//    @Override
//    public String toString() {
//        return "Food{" + "id=" + id + ", name=" + name + ", price=" + price + ", desc=" + desc + ", img=" + img + '}';
//    }
    
}

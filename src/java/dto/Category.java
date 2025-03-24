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
public class Category implements Serializable{
     private static final long serialVersionUID = 1L; // Define a unique version ID

    private String cateId;
    private String nameCate;

    public Category() {
    }

    public Category(String cateId, String nameCate) {
        this.cateId = cateId;
        this.nameCate = nameCate;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }

    @Override
    public String toString() {
        return "Category{" + "cateId=" + cateId + ", nameCate=" + nameCate + '}';
    }

}

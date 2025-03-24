/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class DiscountDTO {
    private int discount_ID;
    private String description;
    private double percentage;
    private Date startDate;
    private Date endDate;

    public DiscountDTO() {
    }

    public DiscountDTO(int discount_ID, String description, double percentage, Date startDate, Date endDate) {
        this.discount_ID = discount_ID;
        this.description = description;
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getDiscount_ID() {
        return discount_ID;
    }

    public void setDiscount_ID(int discount_ID) {
        this.discount_ID = discount_ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "DiscountDTO{" + "discount_ID=" + discount_ID + ", description=" + description + ", percentage=" + percentage + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }
    
    
    
}

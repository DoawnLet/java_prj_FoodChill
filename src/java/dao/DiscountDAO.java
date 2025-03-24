/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.DiscountDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mylib.DBUtils;

/**
 *
 * @author Admin
 */
public class DiscountDAO {
    public List<DiscountDTO> getListDiscount(){
        List<DiscountDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = new DBUtils().getConnection();
            String sql = "Select * from discount";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int discount_ID = Integer.parseInt(rs.getString("Discount_ID"));
                String description = rs.getString("Description");
                double percentage = Double.parseDouble(rs.getString("Percentage"));
                Date startDate = rs.getDate("StartDate");
                Date endDate = rs.getDate("EndDate");
                DiscountDTO discount = new DiscountDTO(discount_ID, description, percentage, startDate, endDate);
                list.add(discount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean create(DiscountDTO discount){
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = new DBUtils().getConnection();
            String sql = "INSERT INTO DISCOUNT (Discount_ID, Description, Percentage, StartDate, EndDate) "
                    + "values (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, discount.getDiscount_ID());
            ps.setString(2, discount.getDescription());
            ps.setDouble(3, discount.getPercentage());
            ps.setDate(4, discount.getStartDate());
            ps.setDate(5, discount.getEndDate());
            int aff = ps.executeUpdate();
            return aff > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(DiscountDTO discount){
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = new DBUtils().getConnection();
            String sql = "UPDATE DISCOUNT SET Percentage = ?, StartDate = ?, EndDate = ? where Discount_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setDouble(1, discount.getPercentage());
            ps.setDate(2, discount.getStartDate());
            ps.setDate(3, discount.getEndDate());
            ps.setInt(4, discount.getDiscount_ID());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean delete(int id){
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = new DBUtils().getConnection();
            String sql = "DELETE From Discount where Discount_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<DiscountDTO> searchByID(Integer id, List<DiscountDTO> list){
        List<DiscountDTO> listSearch = new ArrayList<>();
        if(id != null){
            for (DiscountDTO d : list) {
                if(d.getDiscount_ID() == id){
                    listSearch.add(d);
                }
            }
        }
        return listSearch;
    }
    
    
    public DiscountDTO getDiscount (int id){
        Connection con = null;
        
        try {
            con = DBUtils.getConnection();
            if(con != null){
                String sql = " Select * from Discount Where Discount_ID = ? ";
                PreparedStatement ps= con.prepareCall(sql);
                ps.setInt(1, id);
                
                ResultSet rs = ps.executeQuery();
                if(rs != null){
                    while(rs.next()){
                        return new DiscountDTO(
                              rs.getInt("Discount_ID"), 
                                rs.getString("Description"), 
                                rs.getDouble("Percentage"), 
                                rs.getDate("StartDate"),
                                rs.getDate("EndDate"));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error discount data: " + e.getMessage());
        }finally{
            try {
                if(con != null){
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public boolean checkDiscountID(Integer id){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            con = new DBUtils().getConnection();
            String sql = "select * from Discount where Discount_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        DiscountDAO dao = new DiscountDAO();
        DiscountDTO dis = dao.getDiscount(1);
        System.out.println("DIs: " + dis);
    }

}


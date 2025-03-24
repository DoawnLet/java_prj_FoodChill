/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mylib.DBUtils;

/**
 *
 * @author Asus
 */
public class CategoryDAO {

    public ArrayList<Category> getCate() {
        
        ArrayList<Category> listCate = new ArrayList<>();
        
        try {
            Connection con = DBUtils.getConnection();
            String sql = " SELECT CateID, Name FROM Category ";
            
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            if(rs != null){
                while(rs.next()){
                    String id = rs.getString("CateID");
                    String name = rs.getString("Name");
                    
                    Category category = new Category(id, name);
                    listCate.add(category);
                }
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCate;
    }
    
    
    //admin
    public List<String> getCategoryName() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<>();

        try {
            conn = new DBUtils().getConnection();
            String sql = "select * from category ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean addCategory(Category category) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new DBUtils().getConnection();
            String sql = "insert into Category(CateID, Name) values (?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, category.getCateId());
            ps.setString(2, category.getNameCate());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkCateExist(String cateID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new DBUtils().getConnection();
            String sql = "select * from Category where CateID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, cateID);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Category> getListCategory() {
        List<Category> listCate = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new DBUtils().getConnection();
            String sql = "SELECT * FROM Category";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String cateID = rs.getString("CateID");
                String name = rs.getString("Name");
                Category cate = new Category(cateID, name);
                listCate.add(cate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listCate;
    }

    public boolean updateCategory(Category category) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new DBUtils().getConnection();
            String sql = "UPDATE Category SET Name = ? where CateID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, category.getNameCate());
            ps.setString(2, category.getCateId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean deleteCategory(String cateID) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new DBUtils().getConnection();
            String sql = "Delete From Food where CateID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, cateID);
            ps.executeUpdate();
            ps.close();
            
            sql = "Delete From Category where CateID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, cateID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public List<Category> searchCateByID(String keyword, List<Category> listCate){
        List<Category> listSearch = new ArrayList<>();
        for (Category category : listCate) {
            if(category.getCateId().contains(keyword)){
                listSearch.add(category);
            }
        }
        return listSearch;
    }
    
    
    public static void main(String[] args) {
        CategoryDAO cateDao = new CategoryDAO();
        ArrayList<Category> list = cateDao.getCate();
        for (Category category : list) {
            System.out.println(category);
        }
    }

}

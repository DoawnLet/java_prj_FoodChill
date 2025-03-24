/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Order;
import dto.OrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mylib.DBUtils;
import mylib.Status;

/**
 *
 * @author Asus
 */
public class OrderDetailDAO {

    private static OrderDetailDAO instance;
    private FoodDAO foodDao;

    public OrderDetailDAO(FoodDAO foodDao) {
        this.foodDao = foodDao;
    }

    public static OrderDetailDAO getInstance() {
        if (instance == null) {
            instance = new OrderDetailDAO(FoodDAO.getInstance());
        }
        return instance;
    }

    public List<OrderDetail> findByOrderId(int id) {
        List<OrderDetail> orderDetails = new ArrayList<>();

        Connection con = null;
        try {
            con = DBUtils.getConnection();
            String sql = " Select * from OrderDetail Where Order_ID =  ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    orderDetails.add(new OrderDetail(
                            foodDao.getFoodByID(rs.getString("Food_ID")),
                            rs.getDouble("Quantity"),
                            rs.getInt("UnitPrice")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderDetails;
    }
    
    public static void main(String[] args) {
        OrderDetailDAO dao = OrderDetailDAO.getInstance();
        List<OrderDetail> details = dao.findByOrderId(2);
        for (OrderDetail detail : details) {
            System.out.println(detail);
        }
    }
    
}

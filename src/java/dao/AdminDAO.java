/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Account;
import mylib.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mylib.DBUtils;

/**
 *
 * @author Asus
 */
public class AdminDAO {

    public boolean authenticateAdmin(String username, String password)
            throws SQLException, ClassNotFoundException, Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new DBUtils().getConnection();
            if (con != null) {
                String sql = " SELECT * FROM UserAccount WHERE Username = ? and Password = ? and Role_ID = 'R1' ";
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                

                rs = ps.executeQuery();
                return rs.next();
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean authenticateCustomer(String username, String password)
            throws SQLException, ClassNotFoundException, Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new DBUtils().getConnection();

            if (con != null) {
                String sql = " SELECT * FROM UserAccount WHERE Username = ? and Password = ? and Role_ID = 'R2' ";
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                

                rs = ps.executeQuery();
                return rs.next();
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        AdminDAO dao = new AdminDAO();
        String username = "ngoclan";
        String password = "lanngoc456";

        try {
            Boolean user = dao.authenticateCustomer(username, password);
            System.out.println("✅ Kết quả kiểm tra đăng nhập: " + user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

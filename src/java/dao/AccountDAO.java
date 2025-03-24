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
import java.util.ArrayList;
import java.util.List;
import mylib.DBUtils;

/**
 *
 * @author Asus
 */
public class AccountDAO {

    private static AccountDAO instance;

    private AccountDAO() {
    }

    public static AccountDAO getInstance() {
        if (instance == null) {
            instance = new AccountDAO();
        }
        return instance;
    }

    //Kiểm tra đăng kí tài khoản
    public void signup(String username, String pass, String email, String phone) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "insert into UserAccount (Username, Password, Email, Phone, Role_ID) values(?, ?, ?, ?, ?)";
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, pass);
            ps.setString(3, email);
            ps.setString(4, phone);
            // Nếu tài khoản là admin thì set quyền là admin
            if (username.contains("admin")) {
                ps.setString(5, "R1");
            } else {
                ps.setString(5, "R2");
            }
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Kiểm tra tồn tài khoản  
    public Account checkAccountExist(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "select * from UserAccount where Username = ? and Password = ?";
        Account account = null;
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                account = new Account();
                account.setUserId(rs.getInt("User_ID"));
                account.setUsername(rs.getString("Username"));
                account.setPassword(rs.getString("Password"));
                account.setEmail(rs.getString("Email"));
                account.setPhone(rs.getString("Phone"));
                account.setRole(Role.getRole(rs.getString("Role_ID")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }
    
    public Account findById (int id){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = " SELECT * from UserAccount WHERE User_ID = ?  ";
        Account register = null;
        try {
            conn = new DBUtils().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                register = new Account();
                register.setUserId(rs.getInt("User_ID"));
                register.setUsername(rs.getString("Username"));
                register.setPassword(rs.getString("Password"));
                register.setEmail(rs.getString("Email"));
                register.setPhone(rs.getString("Phone"));
                //Set Role
                register.setRole(Role.getRole(rs.getString("Role_ID")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return register;
    }
    
    
    //admin
    public List<Account> getListUser() {
        List<Account> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new DBUtils().getConnection();
            String sql = "Select * from UserAccount where Role_ID = 'R2'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int user_id = Integer.parseInt(rs.getString("User_ID"));
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                Role role = Role.getRole(rs.getString("Role_ID"));
                String name = rs.getString("Name");
                String street = rs.getString("Street");
                String ward = rs.getString("Ward");
                String district = rs.getString("District");
                String city = rs.getString("City");
                Account account = new Account(user_id, username, password, email, phone, role, name, street, ward, district, city);
                list.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateUser(Account account) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new DBUtils().getConnection();
            String sql = "Update UserAccount set Name = ?, Phone = ?, Password = ?, Street = ?, Ward = ?, District = ?, City = ? where Username = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, account.getName());
            ps.setString(2, account.getPhone());
            ps.setString(3, account.getPassword());
            ps.setString(4, account.getStreet());
            ps.setString(5, account.getWard());
            ps.setString(6, account.getDistrict());
            ps.setString(7, account.getCity());
            ps.setString(8, account.getUsername());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int user_id) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = new DBUtils().getConnection();

            // Xóa dữ liệu từ bảng phụ trước
            String sql = "DELETE FROM Orders WHERE User_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            int aff1 = ps.executeUpdate();
            ps.close();

            sql = "DELETE FROM UserAccount WHERE User_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, user_id);
            int aff2 = ps.executeUpdate();
            ps.close();

            return aff2 > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
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
        return false;
    }

    public List<Account> searchUserByUsername(String username, List<Account> userList) {
        List<Account> list = new ArrayList<>();

        for (Account account : userList) {
            if (account.getUsername().contains(username)) {
                list.add(account);
            }
        }
        return list;
    }

    public Account getAccountByID(int user_ID) {
        Account account = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new DBUtils().getConnection();
            String sql = "Select * from UserAccount where User_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, user_ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("User_ID");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                Role role = Role.getRole(rs.getString("Role_ID"));
                String name = rs.getString("Name");
                String street = rs.getString("Street");
                String ward = rs.getString("Ward");
                String district = rs.getString("District");
                String city = rs.getString("City");
                account = new Account(id, username, password, email, phone, role, name, street, ward, district, city);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }
    
    
    //Test method AccountDAO
    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        
        Account user = dao.checkAccountExist("ngoclan", "lanngoc456");
        if (user != null) {
            System.out.println("Have user push in DAO" + user);
        } else {
            System.out.println("Not have user");
        }
    }

}

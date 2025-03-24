/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Account;
import dto.Food;
import dto.Order;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mylib.DBUtils;
import mylib.Role;
import mylib.Shipper;
import mylib.Status;
import mylib.Utils;

/**
 *
 * @author Asus
 */
public class OrderDAO {

    private static OrderDAO instance;
    private final OrderDetailDAO orderDetailDAO;
    private final AccountDAO accountDAO;

    public OrderDAO(OrderDetailDAO orderDetailDAO, AccountDAO accountDAO) {
        this.orderDetailDAO = orderDetailDAO;
        this.accountDAO = accountDAO;
    }

    public static OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO(
                    OrderDetailDAO.getInstance(),
                    AccountDAO.getInstance());
        }
        return instance;
    }

    public Order findById(int id) {
        Connection con = null;

        try {
            con = DBUtils.getConnection();
            String sql = " SELECT * FROM Orders WHERE Order_ID = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                Account account = accountDAO.findById(rs.getInt("User_ID"));

                String fullAddress = rs.getString("Street") + ", "
                        + rs.getString("Ward") + ", "
                        + rs.getString("District") + ", "
                        + rs.getString("City");

                return new Order(rs.getInt("Order_ID"),
                        rs.getInt("User_ID"),
                        account.getName(),
                        account.getEmail(),
                        account.getPhone(),
                        Status.getStatus(rs.getInt("Status_ID")),
                        rs.getDate("OrderDate"),
                        Shipper.getShipper(rs.getString("ShipID")),
                        fullAddress,
                        rs.getDouble("Total"),
                        orderDetailDAO.findByOrderId(rs.getInt("Order_ID")));
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
        return null;
    }

    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();

        Connection con = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = " SELECT \n"
                        + "    O.Order_ID, \n"
                        + "    U.User_ID, \n"
                        + "    U.Name AS UserName, \n"
                        + "    U.Phone, \n"
                        + "    SUM(OD.UnitPrice) AS TotalPrice, \n"
                        + "    O.OrderDate, \n"
                        + "    O.ShipID, \n"
                        + "    O.Street + ', ' + O.Ward + ', '+ O.District + ', '+ O.City AS Address, \n"
                        + "    O.Status_ID \n"
                        + "FROM Orders AS O\n"
                        + "JOIN UserAccount AS U ON O.User_ID = U.User_ID\n"
                        + "JOIN OrderDetail AS OD ON O.Order_ID = OD.Order_ID\n"
                        + "GROUP BY O.Order_ID, U.User_ID, U.Name, U.Phone, O.OrderDate, O.ShipID, O.Street, O.Ward, O.District, O.City, O.Status_ID; ";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        list.add(new Order(rs.getInt("Order_ID"),
                                rs.getInt("User_ID"),
                                rs.getString("UserName"),
                                rs.getString("Email"),
                                rs.getString("Phone"),
                                Status.getStatus(rs.getInt("Status_ID")),
                                rs.getDate("OrderDate"),
                                Shipper.getShipper(rs.getString("ShipID")),
                                rs.getString("Address"),
                                rs.getDouble("TotalPrice"),
                                orderDetailDAO.findByOrderId(rs.getInt("Order_ID"))));
                    }
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
        return list;
    }


    public int placeOrder(Account customer, String shipperId, Map<Food, Integer> cart) {
        int result = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                con.setAutoCommit(false);

                // 1️⃣ Insert vào bảng Orders
                String sql = "INSERT INTO Orders (User_ID, Status_ID, OrderDate, ShipID, Total, Street, Ward, District, City) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, customer.getUserId());
                ps.setInt(2, Status.PENDING.getId());
                ps.setDate(3, new Date(System.currentTimeMillis()));
                ps.setString(4, shipperId);
                ps.setDouble(5, Utils.getTotalCost(cart));
                ps.setString(6, customer.getStreet());
                ps.setString(7, customer.getWard());
                ps.setString(8, customer.getDistrict());
                ps.setString(9, customer.getCity());

                result = ps.executeUpdate();

                int orderId = -1;
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
                rs.close();
                ps.close();

                if (orderId == -1) {
                    throw new SQLException("Không lấy được Order_ID mới!");
                }

                sql = "INSERT INTO OrderDetail (Order_ID, Food_ID, Quantity, UnitPrice) VALUES (?, ?, ?, ?)";
                ps = con.prepareStatement(sql);

                for (Map.Entry<Food, Integer> entry : cart.entrySet()) {
                    ps.setInt(1, orderId);
                    ps.setString(2, entry.getKey().getId().trim());
                    ps.setInt(3, entry.getValue());
                    ps.setDouble(4, entry.getKey().getPrice());
                    ps.addBatch();
                }

                int[] batchResults = ps.executeBatch();
                System.out.println("Đã chèn " + batchResults.length + " mục vào OrderDetail.");
                con.commit();
            }
        } catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public List<Order> findByCustomerId(int userId) {
        Connection con = null;
        List<Order> orders = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = " SELECT * FROM Orders WHERE User_ID = ? ORDER BY OrderDate DESC ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                accountDAO.findById(userId);
                if (rs != null) {
                    while (rs.next()) {
                        Account account = accountDAO.findById(rs.getInt("User_ID"));

                        String fullAddress = rs.getString("Street") + ", "
                                + rs.getString("Ward") + ", "
                                + rs.getString("District") + ", "
                                + rs.getString("City");

                        orders.add(new Order(rs.getInt("Order_ID"),
                                rs.getInt("User_ID"),
                                account.getName(),
                                account.getEmail(),
                                account.getPhone(),
                                Status.getStatus(rs.getInt("Status_ID")),
                                rs.getDate("OrderDate"),
                                Shipper.getShipper(rs.getString("ShipID")),
                                fullAddress,
                                rs.getDouble("Total"),
                                orderDetailDAO.findByOrderId(rs.getInt("Order_ID"))));
                    }
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
        return orders;
    }
    
    
   

    //admin
    //Test
    public static void main(String[] args) {
        OrderDAO dao = OrderDAO.getInstance();
        List<Order> customer = dao.findByCustomerId(1);
        for (Order order : customer) {
            System.out.println(order);
        }

    }
}

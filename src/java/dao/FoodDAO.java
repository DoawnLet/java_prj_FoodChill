/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Food;
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
public class FoodDAO {

    private static FoodDAO instance;

    private FoodDAO() {
    }

    public static FoodDAO getInstance() {
        if (instance == null) {
            instance = new FoodDAO();
        }
        return instance;
    }

    public List<Food> getCateFood(String cateId) { //Lay danh sach food theo cate boi sql dua len categoryFood.jsp

        List<Food> listFood = new ArrayList<>();

        try {
            Connection con = DBUtils.getConnection();
            String sql = " SELECT Food_ID, Name, Price, Description, Image FROM Food WHERE CateID = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cateId);

            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String foodID = rs.getString("Food_ID");
                    String name = rs.getString("Name");
                    int price = rs.getInt("Price");
                    String description = rs.getString("Description");
                    String image = rs.getString("Image");

                    Food newFood = new Food(foodID, name, price, description, image);
                    listFood.add(newFood);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listFood;
    }

    public Food getFoodByID(String id) {
        Connection con;
        try {
            con = DBUtils.getConnection();
            String sql = " SELECT * from Food WHERE Food_ID =  ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                return new Food(
                        rs.getString("Food_ID"),
                        rs.getString("CateID"),
                        rs.getString("Name"),
                        rs.getDouble("Price"),
                        rs.getString("Description"),
                        rs.getString("Image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Uses for admin
    public boolean addFood(Food food) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new DBUtils().getConnection();

            String sql = "insert into Food(Food_ID, CateID, Name, Description, Price, Image) "
                    + "values (?, ?, ?, ?, ? ,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, food.getId());
            ps.setString(2, food.getCateId());
            ps.setString(3, food.getName());
            ps.setString(4, food.getDesc());
            ps.setDouble(5, food.getPrice());
            ps.setString(6, food.getImg());
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

    public boolean updateFood(Food food) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new DBUtils().getConnection();
            String sql = "update food set Name = ? , Description = ?, Price = ?, Image = ? where Food_ID = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, food.getName());
            ps.setString(2, food.getDesc());
            ps.setDouble(3, food.getPrice());
            ps.setString(4, food.getImg());
            ps.setString(5, food.getId());
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

    public boolean deleteFood(String foodID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new DBUtils().getConnection();
            String sql = "Delete from Food where Food_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, foodID);
            ps.executeUpdate();

            sql = "Delete from AddOrderDetail where Food_ID = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, foodID);
            int aff = ps.executeUpdate();
            return aff > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Food> getFoodList() {
        List<Food> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new DBUtils().getConnection();
            String sql = "select * from food";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String food_ID = rs.getString("Food_ID");
                String cateID = rs.getString("CateID");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                double price = rs.getDouble("Price");
                String image = rs.getString("Image");
                Food food = new Food(food_ID, cateID, name, price, description, image);
                list.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean checkFoodExist(String food_ID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = new DBUtils().getConnection();
            String sql = "select * from Food where food_ID = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, food_ID);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Food> searchFoodByID(String foodID, List<Food> foodList) {
        List<Food> list = new ArrayList<>();
        if (foodID == null || foodID.isEmpty()) {
            return foodList;
        }
        for (Food food : foodList) {
            if (food.getId().contains(foodID)) {
                list.add(food);
            }
        }

        return list;
    }

    public List<Food> searchByNameDesc(String keyword) {
        List<Food> list = new ArrayList<>();
        Connection con = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM Food WHERE Name LIKE ? OR Description LIKE ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(new Food(
                            rs.getString("Food_ID"),
                            rs.getString("Name"),
                            rs.getDouble("Price"),
                            rs.getString("Description"),
                            rs.getString("Image")
                    ));
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

    public List<Food> favourite() {
        List<Food> list = new ArrayList<>();
        Connection con = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = " SELECT TOP 4 * FROM Food ORDER BY NEWID()";
                PreparedStatement ps = con.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        list.add(new Food(rs.getString("Food_ID"),
                                rs.getString("Name"),
                                rs.getDouble("Price"),
                                rs.getString("Description"),
                                rs.getString("Image")));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        FoodDAO dao = FoodDAO.getInstance();
        List<Food> list = dao.searchByNameDesc("Nước");
        for (Food food : list) {
            System.out.println(food);
        }
    }

}

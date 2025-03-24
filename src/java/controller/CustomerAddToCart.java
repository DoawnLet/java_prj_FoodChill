/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FoodDAO;
import dto.Food;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 *
 * @author Asus
 */
public class CustomerAddToCart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            System.out.println("Check Add To Cart");

            if (session.getAttribute("USER") == null) {
                System.out.println("Người dùng chưa đăng nhập!"); 
                out.write("{\"result\": false, \"message\": \"Bạn cần đăng nhập trước!\", \"size\": 0}");
                return;
            }
            System.out.println("Check user in Cart: " + request.getSession().getAttribute("USER"));

            String foodId = request.getParameter("foodId");
            String quantity = request.getParameter("quantity");
            System.out.println("FoodID: " + foodId);
            System.out.println("Quantity: " + quantity);
//            if (foodId == null || foodId.isEmpty()) {
//                System.out.println("Food id error: " + foodId);
//                return;
//            }
//            if (quantity == null || quantity.isEmpty()) {
//                System.out.println("Quantity id error: " + foodId);
//                return;
//            }
            //String ---> int
            int quantityInt;
            try {
                quantityInt = Integer.parseInt(quantity);
            } catch (NumberFormatException e) {
                System.out.println("Change quantity from String to Int error"
                        + e.getMessage());
                return;
            }

            Food selectFood = FoodDAO.getInstance().getFoodByID(foodId);
            System.out.println("Select : " + selectFood);

            Map<Food, Integer> cart = (Map<Food, Integer>) request.getSession().getAttribute("cart");
            System.out.println("Begin create cart: " + cart);
            if (cart == null) {
                cart = new HashMap<>();
                if (quantity != null) {
                    cart.put(selectFood, quantityInt);
                } else {
                    cart.put(selectFood, 1);
                }
            } else {
                Food food = cart.keySet()
                        .stream()
                        .filter(f -> f.getId().equals(selectFood.getId()))
                        .findFirst()
                        .orElse(null);
                if (food != null) {
                    if (quantity != null) {
                        cart.put(food, cart.get(food) + quantityInt);
                    } else {
                        cart.put(food, 1);
                    }
                } else {
                    if (quantity != null) {
                        cart.put(selectFood, quantityInt);
                    } else {
                        cart.put(selectFood, 1);
                    }
                }
            }

            System.out.println("Food in cart: " + cart);

            session.setAttribute("cart", cart);
            out.println("{\"result\": true, \"size\": " + cart.size() + "}");
            //request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

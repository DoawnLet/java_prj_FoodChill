/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dto.Food;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mylib.Utils;

/**
 *
 * @author Asus
 */
public class CustomerUpdateCartServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String foodId = request.getParameter("foodId");
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            System.out.println("Food id update cart: " + foodId);
            System.out.println("Quantity update cart: " + quantity);

            Map<Food, Integer> cart = (Map<Food, Integer>) request.getSession().getAttribute("cart");
            double totalCost = 0;
            if (cart != null) {
                Food food = cart.keySet()
                        .stream()
                        .filter(f -> f.getId().equalsIgnoreCase(foodId))
                        .findFirst().orElse(null);
                if (food != null) {
                    cart.put(food, quantity);
                }
                totalCost = Utils.getTotalCost(cart);
                request.getSession().setAttribute("cart", cart);
                System.out.println("Cart update: " + cart);
                System.out.println("TotalCost: " + totalCost);
            }
            String jsonResponse = "{"
                    + "\"quantity\": " + quantity + ","
                    + "\"FoodId\": \"" + foodId + "\","
                    + "\"totalCost\": " + totalCost
                    + "}";

            System.out.println("JSON response: " + jsonResponse); // Debug phản hồi JSON
            out.write(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"error\": \"Lỗi server!\"}");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dao.CategoryDAO;
import dao.DiscountDAO;
import dao.FoodDAO;
import dao.OrderDAO;
//import dao.UserDAO;
import dto.Account;
import dto.Category;
import dto.DiscountDTO;
import dto.Food;
import dto.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoadingServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            
            FoodDAO foodDao = FoodDAO.getInstance();
            AccountDAO userDao = AccountDAO.getInstance();
            CategoryDAO cateDao = new CategoryDAO();
            OrderDAO orderDao = OrderDAO.getInstance();
            DiscountDAO discountDao = new DiscountDAO();
            HttpSession session = request.getSession(true);
            List<Food> foodList = foodDao.getFoodList();
            List<Account> userList = userDao.getListUser();
            List<Category> cateList = cateDao.getListCategory();
            List<Order> orderList = orderDao.getAll();
            List<DiscountDTO> discountList = discountDao.getListDiscount();
            session.setAttribute("FOOD_LIST", foodList);
            session.setAttribute("USER_LIST", userList);
            session.setAttribute("CATE_LIST", cateList);
            session.setAttribute("ORDER_LIST", orderList);
            session.setAttribute("DISCOUNT_LIST", discountList);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
            rd.forward(request, response);
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

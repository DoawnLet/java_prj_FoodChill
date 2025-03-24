/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
public class MainServlet extends HttpServlet {

    private final String REGISTER_SERVLET = "RegisterServlet";
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGOUT_SERVLET = "LogoutServlet";

    private final String HOME_PAGE = "HomePageServlet";
    private final String CATEGORY_FOOD = "CategoryFoodServlet";
    private final String VIEW_CART = "ViewCartServlet";

    private final String CUSTOMER_ADD_TO_CART = "CustomerAddToCart";
    private final String CUSTOMER_UPDATE_CART = "CustomerUpdateCartServlet";
    private final String CUSTOMER_REMOVE_CART = "CustomerRemoveCartServlet";

    private final String CUSTOMER_PLACE_ORDER = "CustomerPlaceOrderServlet";
    private final String CUSTOMER_ORDER = "CustomerOrderServlet";
    private final String CUSTOMER_ORDER_DETAIL = "CustomerOrderDetailsServlet";
    private final String CUSTOMER_HISTORY_ORDER = "CustomerHistoryOrderServlet";
    private final String CUSTOMER_SEARCH = "CustomerSearchServlet";

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
            String url = "homePage.jsp";
            HttpSession session = request.getSession();

            System.out.println(request.getSession().getAttribute("USER"));
            try {
                String action = request.getParameter("action");
                String button = request.getParameter("btnAction");
                System.out.println("button:" + button);
                System.out.println("Action:" + action);
                if (action == null) {
                    url = HOME_PAGE;
                } else if (action.equals("home")) {
                    url = HOME_PAGE;
                } else if (action.equals("category-food")) {
                    url = CATEGORY_FOOD;
                } else if (action.equals("Login")) {
                    url = LOGIN_SERVLET;
                } else if (action.equals("Register")) {
                    url = REGISTER_SERVLET;
                } else if (action.equals("Logout")) {
                    url = LOGOUT_SERVLET;
                } else if (action.equals("GetUrl")) {
                    url = (String) request.getParameter("url");
                    session.setAttribute("URL", url);
                } else if (action.equals("view-cart")) {
                    url = VIEW_CART;
                } else if (action.equals("add-to-cart")) {
                    url = CUSTOMER_ADD_TO_CART;
                } else if (action.equals("customer-update-cart")) {
                    url = CUSTOMER_UPDATE_CART;
                } else if (action.equals("customer-remove-food-cart")) {
                    url = CUSTOMER_REMOVE_CART;
                } else if (action.equals("customer-place-order")) {
                    url = CUSTOMER_PLACE_ORDER;
                } else if (action.equals("customer-get-order")) {
                    url = CUSTOMER_ORDER;
                } else if (action.equals("customer-get-order-detail")) {
                    url = CUSTOMER_ORDER_DETAIL;
                } else if (action.equals("customer-history-order")) {
                    url = CUSTOMER_HISTORY_ORDER;
                } else if (action.equals("search")) {
                    url = CUSTOMER_SEARCH;
                } else if (button.equals("adminAction")) { //admin
                    session = request.getSession(true);
                    action = request.getParameter("action");
                    session.setAttribute("ADMIN_ACTION", action);
                    System.out.println("Admin: " + action);
                    if (session.getAttribute("FOOD_LIST") == null) {
                        System.out.println("Check Loading Servlet");
                        url = "LoadingServlet";

                    } else {
                        url = "admin.jsp";
                    }
                }
            } finally {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
                out.close();
            }
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OrderDAO;
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
public class AdminOrderServlet extends HttpServlet {

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
        String action = request.getParameter("action");
        HttpSession session = request.getSession(true);
        OrderDAO dao = OrderDAO.getInstance();
        try {
            if(action.equals("Search")){
                String keyword = request.getParameter("keyword");
                System.out.println("Keyword: " + keyword);
                List<Order> list = dao.getAll();
                
                if (keyword == null) {
                    keyword = "";
                }
//                List<Order> listSearch = dao.search(keyword, list);
//                session.setAttribute("ORDER_LIST", listSearch);
                session.setAttribute("ADMIN_ACTION", "order");
                RequestDispatcher rd = request.getRequestDispatcher("MainServlet?btnAction=adminAction&action=order");
                rd.forward(request, response);
            }else if(action.equals("Update")){
                String id = request.getParameter("id");
                int status = Integer.parseInt(request.getParameter("status"));

                if(id != null){
//                    dao.update(id, status);
                }
//                List<OrderDTO> list = dao.getListOrder();
//                session.setAttribute("ORDER_LIST", list);
                RequestDispatcher rd = request.getRequestDispatcher("MainServlet?btnAction=adminAction&action=order");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

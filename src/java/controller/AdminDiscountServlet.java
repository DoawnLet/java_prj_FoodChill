/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DiscountDAO;
import dto.DiscountDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
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
public class AdminDiscountServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        DiscountDAO dao = new DiscountDAO();

        if (action.equals("Search")) {
            String keywordStr = request.getParameter("keyword");
            Integer keyword = null;

            if (keywordStr != null && !keywordStr.trim().isEmpty()) {
                try {
                    keyword = Integer.parseInt(keywordStr);
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Invalid input! Please enter a valid number.");
                    return;
                }
            }

            List<DiscountDTO> list = dao.getListDiscount();
            List<DiscountDTO> listSearch = new ArrayList<>();
            if (keyword != null) {
                listSearch = dao.searchByID(keyword, list);
            } else {
                listSearch = list;
            }
            session.setAttribute("DISCOUNT_LIST", listSearch);
            RequestDispatcher rd = request.getRequestDispatcher("MainServlet?btnAction=adminAction&action=couponCode");
            rd.forward(request, response);
        } else if (action.equals("Add")) {
            request.setAttribute("nextaction", "insert");
            RequestDispatcher rd = request.getRequestDispatcher("addDiscount.jsp");
            rd.forward(request, response);
        } else if (action.equals("insert")) {
            Integer discountID = Integer.parseInt(request.getParameter("discountID"));
            String description = request.getParameter("description");
            double percentage = Double.parseDouble(request.getParameter("percentage"));
            Date startDate = Date.valueOf(request.getParameter("startDate"));
            Date endDate = Date.valueOf(request.getParameter("endDate"));

            DiscountDTO discount = new DiscountDTO(discountID, description, percentage, startDate, endDate);
            boolean checkExist = dao.checkDiscountID(discountID);
            if (checkExist) {
                request.setAttribute("EXISTED", "DiscountID is already existed!!!");
                RequestDispatcher rd = request.getRequestDispatcher("addDiscount.jsp");
                rd.forward(request, response);
            }else{
                List<DiscountDTO> list = dao.getListDiscount();
                dao.create(discount);
                list.add(discount);
                session.setAttribute("DISCOUNT_LIST", list);
                request.setAttribute("SUCCESSFULL", "The discount has been successfully added!!");
                RequestDispatcher rd = request.getRequestDispatcher("addDiscount.jsp");
                rd.forward(request, response);
            }
        } else if(action.equals("Update")){
            Integer discountID = Integer.parseInt(request.getParameter("id"));
            String description = request.getParameter("description");
            double percentage = Double.parseDouble(request.getParameter("percentage"));
            Date startDate = Date.valueOf(request.getParameter("startDate"));
            Date endDate = Date.valueOf(request.getParameter("endDate"));
            
            System.out.println(percentage);
            DiscountDTO discount = new DiscountDTO(discountID, description, percentage, startDate, endDate);
            if(discount != null){
                dao.update(discount);
            }
            List<DiscountDTO> list = dao.getListDiscount();
            session.setAttribute("DISCOUNT_LIST", list);
            RequestDispatcher rd = request.getRequestDispatcher("MainServlet?btnAction=adminAction&action=couponCode");
            rd.forward(request, response);
        } else if(action.equals("Delete")){
            Integer discountID = Integer.parseInt(request.getParameter("id"));
            if(discountID != null){
                dao.delete(discountID);
            }
            List<DiscountDTO> list = dao.getListDiscount();
            session.setAttribute("DISCOUNT_LIST", list);
            RequestDispatcher rd = request.getRequestDispatcher("MainServlet?btnAction=adminAction&action=couponCode");
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

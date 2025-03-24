/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoryDAO;
import dto.Category;
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
public class AdminCategoryServlet extends HttpServlet {

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
        HttpSession session = request.getSession(true);

        try {
            String action = request.getParameter("action");
            CategoryDAO dao = new CategoryDAO();

            if (action.equals("Add")) {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                if (!dao.checkCateExist(id)) {
                    Category category = new Category(id, name);
                    dao.addCategory(category);
                    List<Category> list = dao.getListCategory();
                    session.setAttribute("CATE_LIST", list);
                    RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
                    rd.forward(request, response);
                }
            } else if (action.equals("Update")) {
                String cateID = request.getParameter("cateID");
                String name = request.getParameter("name");
                if (dao.checkCateExist(cateID)) {
                    Category category = new Category(cateID, name);
                    dao.updateCategory(category);
                    List<Category> list = dao.getListCategory();
                    session.setAttribute("CATE_LIST", list);
                    RequestDispatcher rd = request.getRequestDispatcher("MainServlet?btnAction=adminAction&action=category");
                    rd.forward(request, response);
                }
            } else if (action.equals("Delete")) {
                String cateID = request.getParameter("cateID");
                if (dao.checkCateExist(cateID)) {
                    dao.deleteCategory(cateID);
                    List<Category> list = dao.getListCategory();
                    session.setAttribute("CATE_LIST", list);
                    RequestDispatcher rd = request.getRequestDispatcher("MainServlet?btnAction=adminAction&action=category");
                    rd.forward(request, response);
                }
            } else if (action.equals("Search")) {
                String keyword = request.getParameter("keyword");
                System.out.println(keyword);
                if (keyword == null) {
                    keyword = "";
                }

                List<Category> list = dao.getListCategory();
                List<Category> listSearch = dao.searchCateByID(keyword, list);
                session.setAttribute("CATE_LIST", listSearch);
                RequestDispatcher rd = request.getRequestDispatcher("MainServlet?btnAction=adminAction&action=category");
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

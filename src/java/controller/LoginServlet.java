/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dao.AdminDAO;
import dto.Account;
import errors.RegisterError;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
public class LoginServlet extends HttpServlet {

    private final String REGISTER_PAGE = "register.jsp";
    private final String LOGIN_PAGE = "login.jsp";
    private final String HOME_PAGE = "homePage.jsp";
    private final String ADMIN_PAGE = "admin.jsp";

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

        String url = "login.jsp";
        String message = null;
        
        
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            AdminDAO dao = new AdminDAO();
            if (dao.authenticateAdmin(username, password)) {
                
                AccountDAO acDAO = AccountDAO.getInstance();
                Account user = acDAO.checkAccountExist(username, password);
                if (user != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("USER", user);
                    response.sendRedirect("admin.jsp");
                } else {
                    request.setAttribute("ERROR", "Username or password is incorrect");
                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
                }
            } else if (dao.authenticateCustomer(username, password)) {
                Cookie cUsername = new Cookie("USERNAME", username);
                Cookie cPasssword = new Cookie("PASSWORD", password);
                cUsername.setMaxAge(60 * 60);
                cPasssword.setMaxAge(60 * 60);

                response.addCookie(cUsername);
                response.addCookie(cPasssword);

                AccountDAO acDAO = AccountDAO.getInstance();//Lay Account dao 
                Account user = acDAO.checkAccountExist(username, password);
                if (user != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("USER", user);
                    response.sendRedirect("MainServlet");
                } else {
                    request.setAttribute("ERROR", "Username or password is incorrect");
                    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                    rd.forward(request, response);
                }

            } else {
                request.setAttribute("ERROR", "Username or Password is not correct, Try again!");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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

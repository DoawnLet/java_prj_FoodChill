/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dto.Account;
import errors.RegisterError;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Asus
 */

public class RegisterServlet extends HttpServlet {

    private final String REGISTER_PAGE = "register.jsp";
    private final String LOGIN_PAGE = "login.jsp";

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
            System.out.println("Check register");
            String url = LOGIN_PAGE;
            try {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String re_pass = request.getParameter("confirm_password");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                System.out.println("Phone: " + phone);

                RegisterError errors = new RegisterError();
                //Kiểm regex 
                boolean usernameError = errors.checkUserName(username, 5, 15);
                boolean passwordError = errors.checkPass(password, 5, 15);
                boolean re_passError = errors.checkConfirmPassword(re_pass, password);
                boolean emailError = errors.checkEmail(email, 5, 30);
                boolean emailFormat = errors.checkEmailFormat(email);
                boolean phoneError = errors.checkPhone(phone, 5, 15);
                boolean phoneFormat = errors.checkPhoneFormat(phone);

                boolean isValid = (usernameError && passwordError && re_passError && emailError && emailFormat && phoneError && phoneFormat);
                
              
                
                if (isValid) {
                    AccountDAO dao = AccountDAO.getInstance();
                    Account account = dao.checkAccountExist(username,password);
                    if (account == null) {
                        dao.signup(username, password, email, phone);
                        url = "login.jsp";
                    } else {
                        request.setAttribute("EXISTED", "Account already existed!!!");
                        url = REGISTER_PAGE;
                    }
                } else {
                    url = REGISTER_PAGE;
                    request.setAttribute("ERROR", errors);
                }
            } catch (Exception e) {
                e.printStackTrace();
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

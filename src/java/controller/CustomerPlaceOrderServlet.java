/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OrderDAO;
import dto.Account;
import dto.Food;
import dto.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mylib.Utils;

/**
 *
 * @author Asus
 */
public class CustomerPlaceOrderServlet extends HttpServlet {

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
                HttpSession session = request.getSession();

                Map<Food, Integer> cart = (Map<Food, Integer>) session.getAttribute("cart");

                if (cart == null || cart.isEmpty()) {
                    request.setAttribute("message", "Giỏ hàng của bạn đang trống! Vui lòng thêm sản phẩm.");
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                    return;
                }

                String city = Utils.decode(request.getParameter("city"));
                String district = Utils.decode(request.getParameter("district"));
                String ward = Utils.decode(request.getParameter("ward"));
                String street = Utils.decode(request.getParameter("street"));

                Account customer = (Account) request.getSession().getAttribute("USER");

                if (city == null || city.trim().isEmpty()) {
                    city = customer.getCity();
                }
                if (district == null || district.trim().isEmpty()) {
                    district = customer.getDistrict();
                }
                if (ward == null || ward.trim().isEmpty()) {
                    ward = customer.getWard();
                }
                if (street == null || street.trim().isEmpty()) {
                    street = customer.getStreet();
                }

                //Test thông tin mặc định user đã có
                customer.setStreet(street);
                customer.setWard(ward);
                customer.setDistrict(district);
                customer.setCity(city);
                String shipperId = request.getParameter("shipper");

                session.setAttribute("USER", customer);

                OrderDAO orderDAO = OrderDAO.getInstance();
                int result = orderDAO.placeOrder(customer, shipperId, cart);

                System.out.println("Customer: " + customer);

                if (result > 0) {
                    session.removeAttribute("cart");
                    response.sendRedirect("MainServlet?action=customer-get-order"); // Chuyển đến trang đặt hàng thành công
                } else {
                    request.setAttribute("message", "Có lỗi xảy ra khi đặt hàng. Vui lòng thử lại.");
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FoodDAO;
import dto.Food;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import mylib.Utils;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10, //10MB
        maxRequestSize = 1024 * 1024 * 50)

public class AdminFoodServlet extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        String url = "admin.jsp";
        try {
            String action = request.getParameter("action");
            FoodDAO dao = FoodDAO.getInstance();

            if (action.equals("AddOrEdit")) {
                Food food = new Food();
                request.setAttribute("food", food);
                request.setAttribute("nextaction", "Insert");
                RequestDispatcher rd = request.getRequestDispatcher("/addFood.jsp");
                rd.forward(request, response);
            } else if (action.equals("Detail")) {
                String foodID = request.getParameter("id");
                Food food = null;
                if (foodID != null) {
                    food = dao.getFoodByID(foodID);
                }

                request.setAttribute("food", food);
                RequestDispatcher rd = request.getRequestDispatcher("foodDetail.jsp");
                rd.forward(request, response);
            } else if (action.equals("Edit")) {
                String foodID = request.getParameter("id");
                Food food = null;
                if (foodID != null) {
                    food = dao.getFoodByID(foodID);
                }

                request.setAttribute("food", food);
                request.setAttribute("nextaction", "Update");
                RequestDispatcher rd = request.getRequestDispatcher("addFood.jsp");
                rd.forward(request, response);
            } else if (action.equals("Update")) {
                String foodID = request.getParameter("foodID");
                String cateID = request.getParameter(url);
                String name = Utils.decode(request.getParameter("name"));
                String description = Utils.decode(request.getParameter("description"));
                int price = Integer.parseInt(request.getParameter("price"));
                String image = Utils.decode(request.getParameter("image"));

                Food food = null;
                if (foodID != null) {
                    food = dao.getFoodByID(foodID);
                }

                food.setName(name);
                food.setDesc(description);
                food.setPrice(price);
                food.setImg(image);

                dao.updateFood(food);
                request.setAttribute("food", food);

                request.setAttribute("SUCCESSFULL", "Update successfully!!!");
                List<Food> list = dao.getFoodList();
                session.setAttribute("FOOD_LIST", list);
                RequestDispatcher rd = request.getRequestDispatcher("addFood.jsp");
                rd.forward(request, response);
            } else if (action.equals("Insert")) {
                String foodID = request.getParameter("foodID");
                String cateID = request.getParameter("cateID");
                String name = Utils.decode(request.getParameter("name"));
                String description = Utils.decode(request.getParameter("description"));
                double price = Double.parseDouble(request.getParameter("price"));

                Part part = request.getPart("image");
                String cate = "";
                if(cateID.equals("Cat1")){
                    cate = "man";
                }else if(cateID.equals("Cat2")){
                    cate = "chay";
                }else{
                    cate = "doUong";
                }
                String uploadPath = "image" + File.separator + cate; // Đường dẫn tương đối
                String savePath = request.getServletContext().getRealPath("") + File.separator + uploadPath;             

                String fileName = extractFileName(part); // Lấy tên file gốc
                String filePath = uploadPath + File.separator + fileName; // Chỉ lấy đường dẫn tương đối
                part.write(savePath + File.separator + fileName); // Lưu file vào thư mục

                Food food = new Food(foodID, cateID, name, price, description, filePath);
                boolean checkExist = dao.checkFoodExist(foodID);
                if (checkExist) {
                    request.setAttribute("EXISTED", "Food_ID is already existed!!!");
                    RequestDispatcher rd = request.getRequestDispatcher("addFood.jsp");
                    rd.forward(request, response);
                } else {
                    List<Food> list = dao.getFoodList();
                    dao.addFood(food);
                    list.add(food);
                    session.setAttribute("FOOD_LIST", list);
                    request.setAttribute("SUCCESSFULL", "The food has been successfully added!!");
                    RequestDispatcher rd = request.getRequestDispatcher("addFood.jsp");
                    rd.forward(request, response);
                }
            } else if (action.equals("Delete")) {
                String foodID = request.getParameter("id");
                if (foodID != null) {
                    dao.deleteFood(foodID);
                }

                List<Food> list = dao.getFoodList();
                session.setAttribute("FOOD_LIST", list);
                response.sendRedirect("admin.jsp");
            } else if (action.equals("Search")) {
                String keyword = request.getParameter("keyword");
                List<Food> list = dao.getFoodList();

                if (keyword == null) {
                    keyword = "";
                }
                List<Food> listSearch = dao.searchFoodByID(keyword, list);
                session.setAttribute("FOOD_LIST", listSearch);
                RequestDispatcher rd = request.getRequestDispatcher("MainServlet?btnAction=adminAction&action=mystore");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
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

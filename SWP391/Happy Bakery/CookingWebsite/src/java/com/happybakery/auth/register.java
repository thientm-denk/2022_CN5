/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happybakery.auth;

import com.happybakery.dto.User;
import com.happybakery.dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.happybakery.mylib.ImageUploader;

/**
 *
 * @author thinh
 */
public class register extends HttpServlet {

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
            String userType = request.getParameter("usertype");
            String userName = request.getParameter("fullname");
            String userMail = request.getParameter("mail");
            String userPassword = request.getParameter("pass");
            String userGender = request.getParameter("gender");
            String userPhone = request.getParameter("phone");
            String userAddress = request.getParameter("address");
            String userBDay = request.getParameter("date");

            User user = null;
            try {
                user = UserDAO.checkMailExist(userMail);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (!userMail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{3}$")) {
                request.setAttribute("emailError", "Email sai định dạng!");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else if (user != null) {
                request.setAttribute("emailDupli", "Email đã tồn tại!");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else {
                int userStatus = 1;
                boolean checkInsert = false;
                try {
                    checkInsert = UserDAO.insertUser(userName, userMail, userPassword, userPhone, userBDay, userAddress, userGender, userType, userStatus, null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (checkInsert) {
                    request.setAttribute("registerSuccess", "Đăng ký thành công");
                    RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
                    User addedUser = null;
                    try {
                        addedUser = UserDAO.getUser(userMail, userPassword);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    int addedId = addedUser.getUserId();
                    String imgName = "user" + addedId;
                    String path = "C:\\Users\\thinh\\OneDrive\\Documents\\NetBeansProjects\\CookingWebsite\\web\\img";
                    Part imgPart = request.getPart("img");
                    imgName = ImageUploader.upload(imgName, imgPart, path);
                    try {
                        UserDAO.updateImgPath(addedId, "img" + "/" + imgName);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    

                    rd.forward(request, response);
                } else {
                    request.setAttribute("registerFail", "Đăng ký thất bại");
                    RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
                    rd.forward(request, response);
                }

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

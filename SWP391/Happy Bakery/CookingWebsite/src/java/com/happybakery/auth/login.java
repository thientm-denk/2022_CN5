/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happybakery.auth;

import com.happybakery.dto.Order;
import com.happybakery.dto.Recipe;
import com.happybakery.dto.User;
import com.happybakery.dao.OrderDAO;
import com.happybakery.dao.RecipeDAO;
import com.happybakery.dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thinh
 */
public class login extends HttpServlet {

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

            HttpSession session = request.getSession(true);
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String saveLogin = request.getParameter("saveLogin");

            ArrayList<Recipe> listRecipe = new ArrayList<>();

            User user = null;
            boolean checkCookie = false;
            try {
                if (email == null || email.equals("") || password == null || password.equals("")) {
                    Cookie[] c = request.getCookies();
                    String token = "";
                    if (c != null) {
                        for (Cookie aCookie : c) {
                            if (aCookie.getName().equals("selector")) {
                                checkCookie = true;
                                token = aCookie.getValue();
                                //kiem tra token co ton tai trong db hay k, neu co tra ve acc
                                user = UserDAO.getUser(token);
                                if (user != null) {
                                    String userName = user.getUserName();
                                    String userEmail = user.getUserMail();
                                    session.setAttribute("user", user);
                                    session.setAttribute("userName", userName);
                                    session.setAttribute("userEmail", userEmail);
                                    try {
                                        listRecipe = RecipeDAO.getRecipes(userEmail);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    session.setAttribute("listRecipe", listRecipe);
                                } else {
                                    request.setAttribute("errorLogin", "errorLogin");
                                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                                }
                            }
                        }
                        if (checkCookie == false) {
                            request.setAttribute("errorLogin", "errorLogin");
                            request.getRequestDispatcher("Login.jsp").forward(request, response);
                        }
                        if (user.getUserType().equalsIgnoreCase("ADMIN")) {
                            response.sendRedirect("AdminPage.jsp");
                        } else {
                            response.sendRedirect("HomePage.jsp");
                        }
                    } else {
                        request.setAttribute("errorLogin", "errorLogin");
                        request.getRequestDispatcher("Login.jsp").forward(request, response);
                    }
                } else {
                    user = UserDAO.getUser(email, password);
                    if (user != null) {
                        if (user.getUserType().equalsIgnoreCase("ADMIN")) {
                            HttpSession sessionAdmin = request.getSession(true);
                            if (sessionAdmin != null) {
                                sessionAdmin.setAttribute("user", user);
                                sessionAdmin.setAttribute("userName", user.getUserName());
                                sessionAdmin.setAttribute("userEmail", email);
                                if (saveLogin != null) {
                                    Random rand = new Random();
                                    int maxNumber = 1000000000;
                                    int tokenNum = rand.nextInt(maxNumber) + 1;
                                    String token = String.valueOf(tokenNum);
                                    UserDAO.updateUserToken(email, token);
                                    Cookie cookie = new Cookie("selector", token);
                                    cookie.setMaxAge(60 * 60);
                                    response.addCookie(cookie);
                                }
                                response.sendRedirect("AdminPage.jsp");
                            }
                        } else {
                            HttpSession sessionUser = request.getSession(true);
                            if (sessionUser != null) {
                                sessionUser.setAttribute("user", user);
                                sessionUser.setAttribute("userName", user.getUserName());
                                sessionUser.setAttribute("userEmail", email);
                                try {
                                    listRecipe = RecipeDAO.getRecipes(email);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                sessionUser.setAttribute("listRecipe", listRecipe);
                                if (saveLogin != null) {
                                    Random rand = new Random();
                                    int maxNumber = 1000000000;
                                    int tokenNum = rand.nextInt(maxNumber) + 1;
                                    String token = String.valueOf(tokenNum);
                                    UserDAO.updateUserToken(email, token);
                                    Cookie cookie = new Cookie("selector", token);
                                    cookie.setMaxAge(60 * 60);
                                    response.addCookie(cookie);
                                }
                                response.sendRedirect("HomePage.jsp");
                            }
                        }
                    } else {
                        request.setAttribute("errorLogin", "errorLogin");
                        request.getRequestDispatcher("Login.jsp").forward(request, response);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
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

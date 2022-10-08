/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happybakery.page;

import com.happybakery.dto.Blog;
import com.happybakery.dto.Recipe;
import com.happybakery.dto.User;
import com.happybakery.dao.BlogDAO;
import com.happybakery.dao.RecipeDAO;
import com.happybakery.dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thinh
 */
public class getHomePageItems extends HttpServlet {

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
            ArrayList<Recipe> listNewRecipe = new ArrayList<>();
            ArrayList<User> listNewStore = new ArrayList<>();
            ArrayList<Blog> listNewBlog = new ArrayList<>();
            Recipe displayBigNewRecipe = null;
            User displayBigNewStore = null;
            
            //Get recipes
            try {
                listNewRecipe = RecipeDAO.get5NewestRecipe();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            for (Recipe recipe : listNewRecipe) {
                displayBigNewRecipe = recipe;
                listNewRecipe.remove(0);
                break;
            }
                
            //Get users
            try {
                listNewStore = UserDAO.get5NewestStores();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            for (User user : listNewStore) {
                displayBigNewStore = user;
                listNewStore.remove(0);
                break;
            }
            
            //Get blogs
            try {
                listNewBlog = BlogDAO.get3NewestBlog();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            session.setAttribute("listNewRecipe", listNewRecipe);
            session.setAttribute("listNewStore", listNewStore);
            session.setAttribute("listNewBlog", listNewBlog);
            session.setAttribute("displayBigNewRecipe", displayBigNewRecipe);
            session.setAttribute("displayBigNewStore", displayBigNewStore);
            response.sendRedirect("HomePage.jsp");
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

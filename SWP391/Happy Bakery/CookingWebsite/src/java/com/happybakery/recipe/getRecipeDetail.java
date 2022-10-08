/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.happybakery.recipe;

import com.happybakery.dto.CommentRecipe;
import com.happybakery.dto.SpecIngredient;
import com.happybakery.dto.Recipe;
import com.happybakery.dto.Step;
import com.happybakery.dto.User;
import com.happybakery.dao.CommentRecipeDAO;
import com.happybakery.dao.IngredientDAO;
import com.happybakery.dao.RecipeDAO;
import com.happybakery.dao.StepDAO;
import com.happybakery.dao.UserDAO;
import com.happybakery.dto.Ingredient;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thinh
 */
public class getRecipeDetail extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            HttpSession session = request.getSession(true);
            String recipeId = request.getParameter("recipeId");
            Recipe recipe = null;
            ArrayList<Step> listStep = new ArrayList<>();
            ArrayList<CommentRecipe> listCmt = new ArrayList<>();
            ArrayList<Ingredient> listIngredient = new ArrayList<>();
            User user = null;
            
            
            try {
                recipe = RecipeDAO.getRecipesById(Integer.parseInt(recipeId));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            try {
                listStep = StepDAO.getAllStepsById(Integer.parseInt(recipeId));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            try {
                listCmt = CommentRecipeDAO.getAllCommentsById(Integer.parseInt(recipeId));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            try {
                listIngredient = IngredientDAO.getAllIngredientsById(Integer.parseInt(recipeId));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            try {
                user = UserDAO.getUserById(recipe.getUserId());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            session.setAttribute("listStep", listStep);
            session.setAttribute("listCmt", listCmt);
            session.setAttribute("listIngredient", listIngredient);
            session.setAttribute("recipeRecipeDetail", recipe);
            session.setAttribute("userRecipeDetail", user);
            response.sendRedirect("RecipeDetail.jsp");
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

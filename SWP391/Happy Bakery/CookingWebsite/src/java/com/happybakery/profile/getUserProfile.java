/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.happybakery.profile;

import com.happybakery.dao.BlogDAO;
import com.happybakery.dao.OrderDAO;
import com.happybakery.dao.RecipeDAO;
import com.happybakery.dao.SpecIngredientDAO;
import com.happybakery.dao.UserDAO;
import com.happybakery.dto.Badge;
import com.happybakery.dto.Blog;
import com.happybakery.dto.Order;
import com.happybakery.dto.Recipe;
import com.happybakery.dto.SpecIngredient;
import com.happybakery.dto.User;
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
public class getUserProfile extends HttpServlet {

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
            String checkedUserId = request.getParameter("checkedUserId");
            String userIdRedirect = (String) session.getAttribute("userIdRedirect");
            if (checkedUserId == null) {
                checkedUserId = userIdRedirect;
            }
            User userLogined = (User) session.getAttribute("user");
            User user = null;
            int blogPosted = 0;
            int recipePosted = 0;
            int followers = 0;
            int totalRating = 0;
            ArrayList<Badge> badgeList = new ArrayList<>();
            ArrayList<Blog> blogList = new ArrayList<>();
            ArrayList<Recipe> recipeList = new ArrayList<>();
            ArrayList<SpecIngredient> specIngredient = new ArrayList<>();
            ArrayList<Order> orderList = new ArrayList<>();

            try {
                user = UserDAO.getUserById(Integer.parseInt(checkedUserId));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            int userId = user.getUserId();

            try {
                blogPosted = UserDAO.getBlogPosted(userId);
                recipePosted = UserDAO.getRecipePosted(userId);
                followers = UserDAO.getFollowers(userId);
                totalRating = UserDAO.getTotalRating(userId);
                badgeList = UserDAO.getUserBadges(userId);
                blogList = BlogDAO.getBlogByUserId(userId);
                recipeList = RecipeDAO.getRecipeByUserId(userId);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (user.getUserType().equalsIgnoreCase("store")) {
                try {
                    specIngredient = SpecIngredientDAO.getIngredientsById(userId);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                session.setAttribute("specIngredient", specIngredient);
            }

            if (userLogined == null) {
                
            } else if (userLogined != null) {
                if (user.getUserId() == userLogined.getUserId()) {
                    if (user.getUserType().equalsIgnoreCase("store")) {
                        try {
                            orderList = OrderDAO.getAllOrdersStore(userId);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        try {
                            orderList = OrderDAO.getAllOrdersUser(userId);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }

            boolean check = false;
            try {
                check = UserDAO.checkFollow(user.getUserId(), userLogined.getUserId());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            session.setAttribute("pfOrderList", orderList);
            session.setAttribute("followStat", check);
            session.setAttribute("checkedUser", user);
            session.setAttribute("blogPosted", blogPosted);
            session.setAttribute("recipePosted", recipePosted);
            session.setAttribute("followers", followers);
            session.setAttribute("pfTotalRating", totalRating);
            session.setAttribute("pfBadgeList", badgeList);
            session.setAttribute("pfBlogList", blogList);
            session.setAttribute("pfRecipeList", recipeList);
            response.sendRedirect("StoreDetail.jsp");
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

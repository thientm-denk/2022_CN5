/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happybakery.dao;

import com.happybakery.dto.Order;
import com.happybakery.dto.Recipe;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import com.happybakery.mylib.DBUtils;

/**
 *
 * @author thinh
 */
public class RecipeDAO {

    public static ArrayList<Recipe> getRecipes(String email) throws Exception {
        ArrayList<Recipe> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select RecipeID, Users.UserID as 'UserID', Users.UserName as 'UserName', RecipeName, RecipeDescription, RecipeAddedDay, RecipeRatingPoint, \n"
                    + "PrepTime, CookTime, Yields, RecipeImg, Category.CategoryName as 'CategoryName', Country.CountryName as 'CountryName'\n"
                    + "from Users \n"
                    + "join Recipe on Users.UserID = Recipe.UserID\n"
                    + "join Category on Recipe.CategoryID = Category.CategoryID\n"
                    + "join Country on Country.CountryID = Recipe.CountryID\n"
                    + "where Users.UserMail = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "%" + email + "%");
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int RecipeID = table.getInt("RecipeID");
                    int UserID = table.getInt("UserID");
                    String RecipeName = table.getString("RecipeName");
                    String UserName = table.getString("UserName");
                    String RecipeDescription = table.getString("RecipeDescription");
                    String RecipeAddedDay = table.getString("RecipeAddedDay");
                    int RecipeRatingPoint = table.getInt("RecipeRatingPoint");
                    int PrepTime = table.getInt("PrepTime");
                    int CookTime = table.getInt("CookTime");
                    int Yields = table.getInt("Yields");
                    String RecipeImg = table.getString("RecipeImg");
                    String CategoryName = table.getString("CategoryName");
                    String CountryName = table.getString("CountryName");
                    Recipe recipe = new Recipe(RecipeID, UserID, UserName, RecipeName, RecipeDescription, RecipeAddedDay, 0, PrepTime, CookTime, Yields, RecipeImg, CategoryName, CountryName, 0);
                    list.add(recipe);
                }
            }

            for (Recipe recipe : list) {
                sql = "select RatedRecipeID, AVG(RatingPoint) as 'AvgRatingPoint', COUNT(CommentID) as 'NumberOfRating'\n"
                        + "from Recipe\n"
                        + "join CommentRecipe on CommentRecipe.RatedRecipeID = Recipe.RecipeID\n"
                        + "where RatedRecipeID = ?\n"
                        + "group by RatedRecipeID";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, recipe.getRecipeId());
                table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int AvgRatingPoint = table.getInt("AvgRatingPoint");
                        int NumberOfRating = table.getInt("NumberOfRating");
                        recipe.setRecipeRating(AvgRatingPoint);
                        recipe.setNumberOfRatings(NumberOfRating);
                        sql = "update Recipe\n"
                                + "set RecipeRatingPoint = ?\n"
                                + "where RecipeID = ?";
                        pst = cn.prepareStatement(sql);
                        pst.setInt(1, AvgRatingPoint);
                        pst.setInt(2, recipe.getRecipeId());
                        pst.executeUpdate();
                    }
                }
            }
            cn.setAutoCommit(true);
            cn.close();
        }
        return list;
    }

    public static Recipe getRecipesById(int recipeId) throws Exception {
        Recipe recipe = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select Users.UserID as 'UserID', Users.UserName as 'UserName', RecipeName, RecipeDescription, RecipeAddedDay, RecipeRatingPoint, \n"
                    + "PrepTime, CookTime, Yields, RecipeImg, Category.CategoryName as 'CategoryName', Country.CountryName as 'CountryName'\n"
                    + "from Users\n"
                    + "join Recipe on Users.UserID = Recipe.UserID\n"
                    + "join Category on Recipe.CategoryID = Category.CategoryID\n"
                    + "join Country on Country.CountryID = Recipe.CountryID\n"
                    + "where Recipe.RecipeID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, recipeId);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int UserID = table.getInt("UserID");
                    String RecipeName = table.getString("RecipeName");
                    String UserName = table.getString("UserName");
                    String RecipeDescription = table.getString("RecipeDescription");
                    String RecipeAddedDay = table.getString("RecipeAddedDay");
                    int RecipeRatingPoint = table.getInt("RecipeRatingPoint");
                    int PrepTime = table.getInt("PrepTime");
                    int CookTime = table.getInt("CookTime");
                    int Yields = table.getInt("Yields");
                    String RecipeImg = table.getString("RecipeImg");
                    String CategoryName = table.getString("CategoryName");
                    String CountryName = table.getString("CountryName");
                    recipe = new Recipe(recipeId, UserID, UserName, RecipeName, RecipeDescription, RecipeAddedDay, 0, PrepTime, CookTime, Yields, RecipeImg, CategoryName, CountryName, 0);
                }
            }

            sql = "select RatedRecipeID, AVG(RatingPoint) as 'AvgRatingPoint', COUNT(CommentID) as 'NumberOfRating'\n"
                    + "from Recipe\n"
                    + "join CommentRecipe on CommentRecipe.RatedRecipeID = Recipe.RecipeID\n"
                    + "where RatedRecipeID = ?\n"
                    + "group by RatedRecipeID";
            pst = cn.prepareStatement(sql);
            pst.setInt(1, recipe.getRecipeId());
            table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int AvgRatingPoint = table.getInt("AvgRatingPoint");
                    int NumberOfRating = table.getInt("NumberOfRating");
                    recipe.setRecipeRating(AvgRatingPoint);
                    recipe.setNumberOfRatings(NumberOfRating);
                    sql = "update Recipe\n"
                            + "set RecipeRatingPoint = ?\n"
                            + "where RecipeID = ?";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, AvgRatingPoint);
                    pst.setInt(2, recipe.getRecipeId());
                    pst.executeUpdate();
                }
            }
            cn.setAutoCommit(true);
            cn.close();
        }
        return recipe;
    }

    public static ArrayList<Recipe> getAllRecipes() throws Exception {
        ArrayList<Recipe> list = new ArrayList<>();
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);

                String sql = "select RecipeID, Users.UserID as 'UserID', Users.UserName as 'UserName', RecipeName, RecipeDescription, RecipeAddedDay, RecipeRatingPoint, \n"
                        + "PrepTime, CookTime, Yields, RecipeImg, Category.CategoryName as 'CategoryName', Country.CountryName as 'CountryName'\n"
                        + "from Users \n"
                        + "join Recipe on Users.UserID = Recipe.UserID\n"
                        + "join Category on Recipe.CategoryID = Category.CategoryID\n"
                        + "join Country on Country.CountryID = Recipe.CountryID";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int RecipeID = table.getInt("RecipeID");
                        int UserID = table.getInt("UserID");
                        String UserName = table.getString("UserName");
                        String RecipeName = table.getString("RecipeName");
                        String RecipeDescription = table.getString("RecipeDescription");
                        String RecipeAddedDay = table.getString("RecipeAddedDay");
                        int RecipeRatingPoint = table.getInt("RecipeRatingPoint");
                        int PrepTime = table.getInt("PrepTime");
                        int CookTime = table.getInt("CookTime");
                        int Yields = table.getInt("Yields");
                        String RecipeImg = table.getString("RecipeImg");
                        String CategoryName = table.getString("CategoryName");
                        String CountryName = table.getString("CountryName");
                        Recipe recipe = new Recipe(RecipeID, UserID, UserName, RecipeName, RecipeDescription, RecipeAddedDay, 0, PrepTime, CookTime, Yields, RecipeImg, CategoryName, CountryName, 0);
                        list.add(recipe);
                    }
                }

                for (Recipe recipe : list) {
                    sql = "select RatedRecipeID, AVG(RatingPoint) as 'AvgRatingPoint', COUNT(CommentID) as 'NumberOfRating'\n"
                            + "from Recipe\n"
                            + "join CommentRecipe on CommentRecipe.RatedRecipeID = Recipe.RecipeID\n"
                            + "where RatedRecipeID = ?\n"
                            + "group by RatedRecipeID";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, recipe.getRecipeId());
                    table = pst.executeQuery();
                    if (table != null) {
                        while (table.next()) {
                            int AvgRatingPoint = table.getInt("AvgRatingPoint");
                            int NumberOfRating = table.getInt("NumberOfRating");
                            recipe.setRecipeRating(AvgRatingPoint);
                            recipe.setNumberOfRatings(NumberOfRating);
                            sql = "update Recipe\n"
                                    + "set RecipeRatingPoint = ?\n"
                                    + "where RecipeID = ?";
                            pst = cn.prepareStatement(sql);
                            pst.setInt(1, AvgRatingPoint);
                            pst.setInt(2, recipe.getRecipeId());
                            pst.executeUpdate();
                        }
                    }
                }
                cn.setAutoCommit(true);
                cn.close();
            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static ArrayList<Recipe> get5NewestRecipe() throws Exception {
        ArrayList<Recipe> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            cn.setAutoCommit(false);
            String sql = "select top 5 RecipeID, Users.UserID, Users.UserName as 'UserName', RecipeName, RecipeDescription, RecipeAddedDay, RecipeRatingPoint, PrepTime, \n"
                    + "CookTime, Yields, RecipeImg, Category.CategoryName as 'CategoryName', Country.CountryName as 'CountryName'\n"
                    + "from Recipe\n"
                    + "join Category on Recipe.CategoryID = Category.CategoryID\n"
                    + "join Country on Country.CountryID = Recipe.CountryID\n"
                    + "join Users on Users.UserID = Recipe.UserID\n"
                    + "order by RecipeID desc";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int RecipeID = table.getInt("RecipeID");
                    int UserID = table.getInt("UserID");
                    String UserName = table.getString("UserName");
                    String RecipeName = table.getString("RecipeName");
                    String RecipeDescription = table.getString("RecipeDescription");
                    String RecipeAddedDay = table.getString("RecipeAddedDay");
                    int RecipeRatingPoint = table.getInt("RecipeRatingPoint");
                    int PrepTime = table.getInt("PrepTime");
                    int CookTime = table.getInt("CookTime");
                    int Yields = table.getInt("Yields");
                    String RecipeImg = table.getString("RecipeImg");
                    String CategoryName = table.getString("CategoryName");
                    String CountryName = table.getString("CountryName");
                    Recipe recipe = new Recipe(RecipeID, UserID, UserName, RecipeName, RecipeDescription, RecipeAddedDay, 0, PrepTime, CookTime, Yields, RecipeImg, CategoryName, CountryName, 0);
                    list.add(recipe);
                }
            }

            for (Recipe recipe : list) {
                sql = "select RatedRecipeID, AVG(RatingPoint) as 'AvgRatingPoint', COUNT(CommentID) as 'NumberOfRating'\n"
                        + "from Recipe\n"
                        + "join CommentRecipe on CommentRecipe.RatedRecipeID = Recipe.RecipeID\n"
                        + "where RatedRecipeID = ?\n"
                        + "group by RatedRecipeID";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, recipe.getRecipeId());
                table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int AvgRatingPoint = table.getInt("AvgRatingPoint");
                        int NumberOfRating = table.getInt("NumberOfRating");
                        recipe.setRecipeRating(AvgRatingPoint);
                        recipe.setNumberOfRatings(NumberOfRating);
                        sql = "update Recipe\n"
                                + "set RecipeRatingPoint = ?\n"
                                + "where RecipeID = ?";
                        pst = cn.prepareStatement(sql);
                        pst.setInt(1, AvgRatingPoint);
                        pst.setInt(2, recipe.getRecipeId());
                        pst.executeUpdate();
                    }
                }
            }
            cn.setAutoCommit(true);
            cn.close();
        }
        return list;
    }

    public static ArrayList<Recipe> getRecipeByUserId(int userId) throws Exception {
        ArrayList<Recipe> list = new ArrayList<>();
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);

                String sql = "select RecipeID, Users.UserName as 'UserName', RecipeName, RecipeDescription, RecipeAddedDay, RecipeRatingPoint, \n"
                        + "PrepTime, CookTime, Yields, RecipeImg, Category.CategoryName as 'CategoryName', Country.CountryName as 'CountryName'\n"
                        + "from Users \n"
                        + "join Recipe on Users.UserID = Recipe.UserID\n"
                        + "join Category on Recipe.CategoryID = Category.CategoryID\n"
                        + "join Country on Country.CountryID = Recipe.CountryID\n"
                        + "where Users.UserID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, userId);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int RecipeID = table.getInt("RecipeID");
                        String UserName = table.getString("UserName");
                        String RecipeName = table.getString("RecipeName");
                        String RecipeDescription = table.getString("RecipeDescription");
                        String RecipeAddedDay = table.getString("RecipeAddedDay");
                        int RecipeRatingPoint = table.getInt("RecipeRatingPoint");
                        int PrepTime = table.getInt("PrepTime");
                        int CookTime = table.getInt("CookTime");
                        int Yields = table.getInt("Yields");
                        String RecipeImg = table.getString("RecipeImg");
                        String CategoryName = table.getString("CategoryName");
                        String CountryName = table.getString("CountryName");
                        Recipe recipe = new Recipe(RecipeID, userId, UserName, RecipeName, RecipeDescription, RecipeAddedDay, 0, PrepTime, CookTime, Yields, RecipeImg, CategoryName, CountryName, 0);
                        list.add(recipe);
                    }
                }

                for (Recipe recipe : list) {
                    sql = "select RatedRecipeID, AVG(RatingPoint) as 'AvgRatingPoint', COUNT(CommentID) as 'NumberOfRating'\n"
                            + "from Recipe\n"
                            + "join CommentRecipe on CommentRecipe.RatedRecipeID = Recipe.RecipeID\n"
                            + "where RatedRecipeID = ?\n"
                            + "group by RatedRecipeID";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, recipe.getRecipeId());
                    table = pst.executeQuery();
                    if (table != null) {
                        while (table.next()) {
                            int AvgRatingPoint = table.getInt("AvgRatingPoint");
                            int NumberOfRating = table.getInt("NumberOfRating");
                            recipe.setRecipeRating(AvgRatingPoint);
                            recipe.setNumberOfRatings(NumberOfRating);
                            sql = "update Recipe\n"
                                    + "set RecipeRatingPoint = ?\n"
                                    + "where RecipeID = ?";
                            pst = cn.prepareStatement(sql);
                            pst.setInt(1, AvgRatingPoint);
                            pst.setInt(2, recipe.getRecipeId());
                            pst.executeUpdate();
                        }
                    }
                }
                cn.setAutoCommit(true);
                cn.close();
            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}

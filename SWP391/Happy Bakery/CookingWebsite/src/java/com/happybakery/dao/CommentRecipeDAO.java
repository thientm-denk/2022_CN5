/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happybakery.dao;

import com.happybakery.dto.CommentRecipe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.happybakery.mylib.DBUtils;

/**
 *
 * @author thinh
 */
public class CommentRecipeDAO {

    public static ArrayList<CommentRecipe> getAllCommentsById(int recipeId) throws Exception {
        ArrayList<CommentRecipe> list = new ArrayList<>();
        //step 1: tạo connection 
        Connection cn = com.happybakery.mylib.DBUtils.makeConnection();
        if (cn != null) {
            //Step 2: viết sql 
            String sql = "select CommentID, RatedRecipeID, Users.UserID as 'UserID', UserName, UserImg, CmtDescription, RatingPoint\n"
                    + "from CommentRecipe\n"
                    + "join Users on Users.UserID = CommentRecipe.UserID\n"
                    + "where RatedRecipeID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, recipeId);
            ResultSet rs = pst.executeQuery();
            //Step 3: xử lý kết quả step2 
            if (rs != null) {
                while (rs.next()) {
                    int CommentID = rs.getInt("CommentID");
                    int RatedRecipeID = rs.getInt("RatedRecipeID");
                    int UserID = rs.getInt("UserID");
                    String UserName = rs.getString("UserName");
                    String UserImg = rs.getString("UserImg");
                    String CmtDescription = rs.getString("CmtDescription");
                    int RatingPoint = rs.getInt("RatingPoint");
                    CommentRecipe commentRecipe = new CommentRecipe(CommentID, RatedRecipeID, UserID, UserName, UserImg, CmtDescription, RatingPoint);
                    list.add(commentRecipe);
                }// hết while 
            }//hết if
            cn.close();
        }
        return list;
    }

    public static boolean insertRecipeComment(int recipeId, int userId, String cmtDescription, int stars) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "insert into CommentRecipe(RatedRecipeID, UserID, CmtDescription, RatingPoint) values (?, ?, ?, ?)";
                PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, recipeId);
            pst.setInt(2, userId);
            pst.setString(3, cmtDescription);
            pst.setInt(4, stars);
            result = pst.executeUpdate();
        }

        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }
}

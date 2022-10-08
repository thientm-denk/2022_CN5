/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happybakery.dao;

import com.happybakery.dto.CommentBlog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.happybakery.mylib.DBUtils;

/**
 *
 * @author thinh
 */
public class CommentBlogDAO {
    public static ArrayList<CommentBlog> getAllCommentsById(int blogId) throws Exception {
        ArrayList<CommentBlog> list = new ArrayList<>();
        //step 1: tạo connection 
        Connection cn = com.happybakery.mylib.DBUtils.makeConnection();
        if (cn != null) {
            //Step 2: viết sql 
            String sql = "select CommentID, RatedBlogID, Users.UserID as 'UserID', UserName, UserImg, CmtDescription, RatingPoint\n"
                    + "from CommentBlog\n"
                    + "join Users on Users.UserID = CommentBlog.UserID\n"
                    + "where RatedBlogID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, blogId);
            ResultSet rs = pst.executeQuery();
            //Step 3: xử lý kết quả step2 
            if (rs != null) {
                while (rs.next()) {
                    int CommentID = rs.getInt("CommentID");
                    int RatedBlogID = rs.getInt("RatedBlogID");
                    int UserID = rs.getInt("UserID");
                    String UserName = rs.getString("UserName");
                    String UserImg = rs.getString("UserImg");
                    String CmtDescription = rs.getString("CmtDescription");
                    int RatingPoint = rs.getInt("RatingPoint");
                    CommentBlog commentBlog = new CommentBlog(CommentID, RatedBlogID, UserID, UserName, UserImg, CmtDescription, RatingPoint);
                    list.add(commentBlog);
                }// hết while 
            }//hết if
            cn.close();
        }
        return list;
    }
    
    public static boolean insertBlogComment(int blogId, int userId, String cmtDescription, int stars) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "insert into CommentBlog(RatedBlogID, UserID, CmtDescription, RatingPoint) values (?, ?, ?, ?)";
                PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, blogId);
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

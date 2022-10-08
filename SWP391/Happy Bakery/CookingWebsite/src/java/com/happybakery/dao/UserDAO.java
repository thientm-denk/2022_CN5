/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happybakery.dao;

import com.happybakery.dto.Badge;
import com.happybakery.dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.happybakery.mylib.DBUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thinh
 */
public class UserDAO {

    public static boolean removeFollowers(int followed, int follower) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "DELETE FROM Follower \n"
                    + "WHERE FollowedId = ? and FollowerId = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, followed);
            pst.setInt(2, follower);
            result = pst.executeUpdate();
        }
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean insertFollowers(int followed, int follower) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "insert into Follower values (?, ?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, followed);
            pst.setInt(2, follower);
            result = pst.executeUpdate();
        }
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkFollow(int followedId, int followerId) throws Exception {
        boolean check = false;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select FollowedId, FollowerId\n"
                    + "from Follower\n"
                    + "where FollowedId = ? and FollowerId = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, followedId);
            pst.setInt(2, followerId);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                check = true;
            } else {
                return check;
            }
            cn.close();
        }
        return check;
    }

    public static User getUser(String email, String password) throws Exception {
        User user = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select UserID, UserName, UserPhone, UserBDay, UserAddress, UserGender, UserType, UserStatus, UserImg\n"
                    + "from Users\n"
                    + "where UserMail = ? and UserPassword = ? and UserStatus = 1";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                int userID = table.getInt("UserID");
                String userName = table.getString("UserName");
                String userPhone = table.getString("UserPhone");
                String userBDay = table.getString("UserBDay");
                String userAddress = table.getString("UserAddress");
                String userGender = table.getString("UserGender");
                String userType = table.getString("UserType");
                int userStatus = table.getInt("UserStatus");
                String userImg = table.getString("UserImg");
                user = new User(userID, userName, email, password, userPhone, userBDay, userAddress, userGender, userType, userStatus, userImg);
            }
            cn.close();
        }
        return user;
    }

    public static User getUser(String token) throws Exception {
        User user = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select UserID, UserMail, UserPassword, UserName, UserPhone, UserBDay, UserAddress, UserGender, UserType, UserStatus, UserImg\n"
                    + "from Users \n"
                    + "where UserToken = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            //gan email, pass vao dau ?
            pst.setString(1, token);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                int userID = table.getInt("UserID");
                String userName = table.getString("UserName");
                String userMail = table.getString("UserMail");
                String userPassword = table.getString("UserPassword");
                String userPhone = table.getString("UserPhone");
                String userBDay = table.getString("UserBDay");
                String userAddress = table.getString("UserAddress");
                String userGender = table.getString("UserGender");
                String userType = table.getString("UserType");
                int userStatus = table.getInt("UserStatus");
                String userImg = table.getString("UserImg");
                user = new User(userID, userName, userMail, userPassword, userPhone, userBDay, userAddress, userGender, userType, userStatus, userImg);
            }
            cn.close();
        }
        return user;
    }

    public static User getUserById(int userId) throws Exception {
        User user = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select UserMail, UserPassword, UserName, UserPhone, UserBDay, UserAddress, UserGender, UserType, UserStatus, UserImg\n"
                    + "from Users \n"
                    + "where UserID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            //gan email, pass vao dau ?
            pst.setInt(1, userId);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                String userName = table.getString("UserName");
                String userMail = table.getString("UserMail");
                String userPassword = table.getString("UserPassword");
                String userPhone = table.getString("UserPhone");
                String userBDay = table.getString("UserBDay");
                String userAddress = table.getString("UserAddress");
                String userGender = table.getString("UserGender");
                String userType = table.getString("UserType");
                int userStatus = table.getInt("UserStatus");
                String userImg = table.getString("UserImg");
                user = new User(userId, userName, userMail, userPassword, userPhone, userBDay, userAddress, userGender, userType, userStatus, userImg);
            }
            cn.close();
        }
        return user;
    }

    public static boolean updateUserToken(String email, String token) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update Users\n"
                    + "set UserToken = ?\n"
                    + "where UserMail = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, token);
            pst.setString(2, email);
            result = pst.executeUpdate();
        }
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static User checkMailExist(String email) throws Exception {
        User user = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select UserID, UserPassword, UserName, UserPhone, UserBDay, UserAddress, UserGender, UserType, UserStatus, UserImg\n"
                    + "from Users\n"
                    + "where UserMail = ?";
            PreparedStatement pst = cn.prepareStatement(sql);

            //gan email, pass vao dau ?
            pst.setString(1, email);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                int userID = table.getInt("UserID");
                String userName = table.getString("UserName");
                String userPassword = table.getString("UserPassword");
                String userPhone = table.getString("UserPhone");
                String userBDay = table.getString("UserBDay");
                String userAddress = table.getString("UserAddress");
                String userGender = table.getString("UserGender");
                String userType = table.getString("UserType");
                int userStatus = table.getInt("UserStatus");
                String userImg = table.getString("UserImg");
                user = new User(userID, userName, email, userPassword, userPhone, userBDay, userAddress, userGender, userType, userStatus, userImg);
            }
            cn.close();
        }
        return user;
    }

    public static int getRecipePosted(int userId) throws Exception {
        int RecipePosted = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select UserID, COUNT(RecipeID) as 'RecipePosted'\n"
                    + "from Recipe\n"
                    + "where UserID = ?\n"
                    + "group by UserID ";
            PreparedStatement pst = cn.prepareStatement(sql);

            //gan email, pass vao dau ?
            pst.setInt(1, userId);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                RecipePosted = table.getInt("RecipePosted");
            }
            cn.close();
        }
        return RecipePosted;
    }

    public static int getBlogPosted(int userId) throws Exception {
        int BlogPosted = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select UserID, COUNT(BlogID) as 'BlogPosted'\n"
                    + "from Blog\n"
                    + "where UserID = ?\n"
                    + "group by UserID ";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, userId);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                BlogPosted = table.getInt("BlogPosted");
            }
            cn.close();
        }
        return BlogPosted;
    }

    public static ArrayList<Badge> getUserBadges(int userId) throws Exception {
        ArrayList<Badge> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select Badge.BadgeID as 'BadgeID', Badge.BadgeName as 'BadgeName'\n"
                    + "from Badge\n"
                    + "join BadgeReward on Badge.BadgeID = BadgeReward.BadgeID\n"
                    + "where UserID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, userId);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int BadgeID = table.getInt("BadgeID");
                    String BadgeName = table.getString("BadgeName");
                    Badge badge = new Badge(userId, BadgeID, BadgeName);
                    list.add(badge);
                }
            }
            cn.close();
        }
        return list;
    }

    public static int getFollowers(int userId) throws Exception {
        int NumOfFollowers = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select FollowedId, COUNT(FollowerId) as 'NumOfFollowers'\n"
                    + "from Follower\n"
                    + "where FollowedId = ?\n"
                    + "group by FollowedId";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, userId);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                NumOfFollowers = table.getInt("NumOfFollowers");
            }
            cn.close();
        }
        return NumOfFollowers;
    }

    public static ArrayList<Integer> getBlogRating(int userId) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select Blog.UserID, RatingPoint\n"
                    + "from Blog \n"
                    + "join CommentBlog on CommentBlog.RatedBlogID = Blog.BlogID\n"
                    + "where Blog.UserID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, userId);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int RatingPoint = table.getInt("RatingPoint");
                    list.add(RatingPoint);
                }
            }
            cn.close();
        }
        return list;
    }

    public static ArrayList<Integer> getRecipeRating(int userId) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select Recipe.UserID, RatingPoint\n"
                    + "from Recipe \n"
                    + "join CommentRecipe on CommentRecipe.RatedRecipeID = Recipe.RecipeID\n"
                    + "where Recipe.UserID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, userId);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int RatingPoint = table.getInt("RatingPoint");
                    list.add(RatingPoint);
                }
            }
            cn.close();
        }
        return list;
    }

    public static int getTotalRating(int userId) {
        int totalRating = 0;
        ArrayList<Integer> blogList = new ArrayList<>();
        ArrayList<Integer> recipeList = new ArrayList<>();

        try {
            blogList = getBlogRating(userId);
            recipeList = getRecipeRating(userId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (Integer integer : recipeList) {
            blogList.add(integer);
        }
        for (Integer integer : blogList) {
            totalRating += integer;
        }
        if (blogList.size() == 0) {
            return 0;
        }
        return (totalRating / blogList.size());
    }

    public static boolean insertUser(String userName, String UserMail, String UserPassword,
            String UserPhone, String UserBDay, String UserAddress, String UserGender, String UserType, int UserStatus, String UserImg) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "insert into Users (UserName, UserMail, UserPassword, UserPhone, UserBDay, UserAddress, UserGender, UserType, UserStatus, UserImg)\n"
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, userName);
            pst.setString(2, UserMail);
            pst.setString(3, UserPassword);
            pst.setString(4, UserPhone);
            pst.setString(5, UserBDay);
            pst.setString(6, UserAddress);
            pst.setString(7, UserGender);
            pst.setString(8, UserType);
            pst.setInt(9, UserStatus);
            pst.setString(10, UserImg);
            result = pst.executeUpdate();
        }

        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<User> get5NewestStores() throws Exception {
        ArrayList<User> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select top 5 UserID, UserName, UserMail, UserPassword, UserPhone, UserBDay, UserAddress, UserGender, UserType, UserStatus, UserImg  \n"
                    + "from Users\n"
                    + "where UserType = 'store'\n"
                    + "order by UserID desc";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int userID = table.getInt("UserID");
                    String userName = table.getString("UserName");
                    String userMail = table.getString("UserMail");
                    String userPassword = table.getString("UserPassword");
                    String userPhone = table.getString("UserPhone");
                    String userBDay = table.getString("UserBDay");
                    String userAddress = table.getString("UserAddress");
                    String userGender = table.getString("UserGender");
                    String userType = table.getString("UserType");
                    int userStatus = table.getInt("UserStatus");
                    String userImg = table.getString("UserImg");
                    User user = new User(userID, userName, userMail, userPassword, userPhone, userBDay, userAddress, userGender, userType, userStatus, userImg);
                    list.add(user);
                }
            }
            cn.close();
        }
        return list;
    }

    public static boolean updateImgPath(int id, String imgPath) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update Users\n"
                    + "set UserImg = ?\n"
                    + "where UserID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, imgPath);
            pst.setInt(2, id);
            result = pst.executeUpdate();
        }

        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<User> getAllStores() throws Exception {
        ArrayList<User> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select UserID, UserName, UserMail, UserPassword, UserPhone, UserBDay, UserAddress, UserGender, UserType, UserStatus, UserImg\n"
                    + "from Users\n"
                    + "where UserType = 'store'";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int userID = table.getInt("UserID");
                    String userName = table.getString("UserName");
                    String userMail = table.getString("UserMail");
                    String userPassword = table.getString("UserPassword");
                    String userPhone = table.getString("UserPhone");
                    String userBDay = table.getString("UserBDay");
                    String userAddress = table.getString("UserAddress");
                    String userGender = table.getString("UserGender");
                    String userType = table.getString("UserType");
                    int userStatus = table.getInt("UserStatus");
                    String userImg = table.getString("UserImg");
                    User user = new User(userID, userName, userMail, userPassword, userPhone, userBDay, userAddress, userGender, userType, userStatus, userImg);
                    list.add(user);
                }
            }
            cn.close();
        }
        return list;
    }

}

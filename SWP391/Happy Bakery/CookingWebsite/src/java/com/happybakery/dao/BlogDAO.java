/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happybakery.dao;

import com.happybakery.dto.Blog;
import com.happybakery.dto.CommentBlog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.happybakery.mylib.DBUtils;

/**
 *
 * @author thinh
 */
public class BlogDAO {

    public static ArrayList<Blog> get3NewestBlog() throws Exception {
        ArrayList<Blog> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            cn.setAutoCommit(false);

            String sql = "select top 3 BlogID, Users.UserID as 'UserID', Users.UserName as 'UserName', BlogTitle, BlogImg, BlogAddedDay, BlogRatingPoint,\n"
                    + "BlogNote, BlogDescription, BlogCategory.BlogCategoryName as 'BlogCategory'\n"
                    + "from Users \n"
                    + "join Blog on Users.UserID = Blog.UserID\n"
                    + "join BlogCategory on Blog.BlogCategoryID = BlogCategory.BlogCategoryID\n"
                    + "order by BlogID desc";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int BlogID = table.getInt("BlogID");
                    int UserID = table.getInt("UserID");
                    String UserName = table.getString("UserName");
                    String BlogTitle = table.getString("BlogTitle");
                    String BlogDescription = table.getString("BlogDescription");
                    String BlogAddedDay = table.getString("BlogAddedDay");
                    int BlogRatingPoint = table.getInt("BlogRatingPoint");
                    String BlogImg = table.getString("BlogImg");
                    String BlogCategory = table.getString("BlogCategory");
                    String BlogNote = table.getString("BlogNote");
                    Blog blog = new Blog(BlogID, UserID, UserName, BlogImg, BlogAddedDay, BlogTitle, BlogNote, BlogDescription, BlogCategory, 0, 0);
                    list.add(blog);
                }
            }

            for (Blog blog : list) {
                sql = "select RatedBlogID, AVG(RatingPoint) as 'AvgRatingPoint', COUNT(CommentID) as 'NumberOfRating'\n"
                        + "from Blog\n"
                        + "join CommentBlog on CommentBlog.RatedBlogID = Blog.BlogID\n"
                        + "where RatedBlogID = ?\n"
                        + "group by RatedBlogID";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, blog.getBlogId());
                table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int AvgRatingPoint = table.getInt("AvgRatingPoint");
                        int NumberOfRating = table.getInt("NumberOfRating");
                        blog.setBlogRatingPoint(AvgRatingPoint);
                        blog.setBlogNumberOfRating(NumberOfRating);
                        sql = "update Blog\n"
                                + "set BlogRatingPoint = ?\n"
                                + "where BlogID = ?";
                        pst = cn.prepareStatement(sql);
                        pst.setInt(1, AvgRatingPoint);
                        pst.setInt(2, blog.getBlogId());
                        pst.executeUpdate();
                    }
                }
            }
            cn.setAutoCommit(true);
            cn.close();
        }
        return list;
    }

    public static Blog getBlogById(int blogId) throws Exception {
        Blog blog = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            cn.setAutoCommit(false);

            String sql = "select Users.UserID as 'UserID', Users.UserName as 'UserName', BlogTitle, BlogImg, BlogAddedDay, BlogRatingPoint,\n"
                    + "BlogNote, BlogDescription, BlogCategory.BlogCategoryName as 'BlogCategory'\n"
                    + " from Users \n"
                    + "join Blog on Users.UserID = Blog.UserID\n"
                    + "join BlogCategory on Blog.BlogCategoryID = BlogCategory.BlogCategoryID\n"
                    + "where Blog.BlogID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, blogId);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int UserID = table.getInt("UserID");
                    String UserName = table.getString("UserName");
                    String BlogTitle = table.getString("BlogTitle");
                    String BlogDescription = table.getString("BlogDescription");
                    String BlogAddedDay = table.getString("BlogAddedDay");
                    int BlogRatingPoint = table.getInt("BlogRatingPoint");
                    String BlogImg = table.getString("BlogImg");
                    String BlogCategory = table.getString("BlogCategory");
                    String BlogNote = table.getString("BlogNote");
                    blog = new Blog(blogId, UserID, UserName, BlogImg, BlogAddedDay, BlogTitle, BlogNote, BlogDescription, BlogCategory, 0, 0);
                }
            }

            sql = "select RatedBlogID, AVG(RatingPoint) as 'AvgRatingPoint', COUNT(CommentID) as 'NumberOfRating'\n"
                    + "from Blog\n"
                    + "join CommentBlog on CommentBlog.RatedBlogID = Blog.BlogID\n"
                    + "where RatedBlogID = ?\n"
                    + "group by RatedBlogID";
            pst = cn.prepareStatement(sql);
            pst.setInt(1, blog.getBlogId());
            table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int AvgRatingPoint = table.getInt("AvgRatingPoint");
                    int NumberOfRating = table.getInt("NumberOfRating");
                    blog.setBlogRatingPoint(AvgRatingPoint);
                    blog.setBlogNumberOfRating(NumberOfRating);
                    sql = "update Blog\n"
                            + "set BlogRatingPoint = ?\n"
                            + "where BlogID = ?";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, AvgRatingPoint);
                    pst.setInt(2, blog.getBlogId());
                    pst.executeUpdate();
                }
            }
            cn.setAutoCommit(true);
            cn.close();
        }
        return blog;
    }
    
    public static ArrayList<Blog> getBlogByUserId(int userId) throws Exception {
        ArrayList<Blog> list = new ArrayList<>();
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);

                String sql = "select Blog.BlogID as 'BlogID', Users.UserName as 'UserName', BlogTitle, BlogImg, BlogAddedDay, BlogRatingPoint,\n"
                    + "BlogNote, BlogDescription, BlogCategory.BlogCategoryName as 'BlogCategory'\n"
                    + "from Users \n"
                    + "join Blog on Users.UserID = Blog.UserID\n"
                    + "join BlogCategory on Blog.BlogCategoryID = BlogCategory.BlogCategoryID\n"
                    + "where Users.UserID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, userId);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int BlogID = table.getInt("BlogID");
                        String UserName = table.getString("UserName");
                        String BlogTitle = table.getString("BlogTitle");
                        String BlogDescription = table.getString("BlogDescription");
                        String BlogAddedDay = table.getString("BlogAddedDay");
                        int BlogRatingPoint = table.getInt("BlogRatingPoint");
                        String BlogImg = table.getString("BlogImg");
                        String BlogCategory = table.getString("BlogCategory");
                        String BlogNote = table.getString("BlogNote");
                        Blog blog = new Blog(BlogID, userId, UserName, BlogImg, BlogAddedDay, BlogTitle, BlogNote, BlogDescription, BlogCategory, 0, 0);
                        list.add(blog);
                    }
                }

                for (Blog blog : list) {
                    sql = "select RatedBlogID, AVG(RatingPoint) as 'AvgRatingPoint', COUNT(CommentID) as 'NumberOfRating'\n"
                            + "from Blog\n"
                            + "join CommentBlog on CommentBlog.RatedBlogID = Blog.BlogID\n"
                            + "where RatedBlogID = ?\n"
                            + "group by RatedBlogID";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, blog.getBlogId());
                    table = pst.executeQuery();
                    if (table != null) {
                        while (table.next()) {
                            int AvgRatingPoint = table.getInt("AvgRatingPoint");
                            int NumberOfRating = table.getInt("NumberOfRating");
                            blog.setBlogRatingPoint(AvgRatingPoint);
                            blog.setBlogNumberOfRating(NumberOfRating);
                            sql = "update Blog\n"
                                    + "set BlogRatingPoint = ?\n"
                                    + "where BlogID = ?";
                            pst = cn.prepareStatement(sql);
                            pst.setInt(1, AvgRatingPoint);
                            pst.setInt(2, blog.getBlogId());
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

    public static ArrayList<Blog> getAllBlogs() throws Exception {
        ArrayList<Blog> list = new ArrayList<>();
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                cn.setAutoCommit(false);

                String sql = "select BlogID, Users.UserID as 'UserID', Users.UserName as 'UserName', BlogTitle, BlogImg, BlogAddedDay, BlogRatingPoint,\n"
                        + "BlogNote, BlogDescription, BlogCategory.BlogCategoryName as 'BlogCategory'\n"
                        + "from Users \n"
                        + "join Blog on Users.UserID = Blog.UserID\n"
                        + "join BlogCategory on Blog.BlogCategoryID = BlogCategory.BlogCategoryID";
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int BlogID = table.getInt("BlogID");
                        int UserID = table.getInt("UserID");
                        String UserName = table.getString("UserName");
                        String BlogTitle = table.getString("BlogTitle");
                        String BlogDescription = table.getString("BlogDescription");
                        String BlogAddedDay = table.getString("BlogAddedDay");
                        int BlogRatingPoint = table.getInt("BlogRatingPoint");
                        String BlogImg = table.getString("BlogImg");
                        String BlogCategory = table.getString("BlogCategory");
                        String BlogNote = table.getString("BlogNote");
                        Blog blog = new Blog(BlogID, UserID, UserName, BlogImg, BlogAddedDay, BlogTitle, BlogNote, BlogDescription, BlogCategory, 0, 0);
                        list.add(blog);
                    }
                }

                for (Blog blog : list) {
                    sql = "select RatedBlogID, AVG(RatingPoint) as 'AvgRatingPoint', COUNT(CommentID) as 'NumberOfRating'\n"
                            + "from Blog\n"
                            + "join CommentBlog on CommentBlog.RatedBlogID = Blog.BlogID\n"
                            + "where RatedBlogID = ?\n"
                            + "group by RatedBlogID";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, blog.getBlogId());
                    table = pst.executeQuery();
                    if (table != null) {
                        while (table.next()) {
                            int AvgRatingPoint = table.getInt("AvgRatingPoint");
                            int NumberOfRating = table.getInt("NumberOfRating");
                            blog.setBlogRatingPoint(AvgRatingPoint);
                            blog.setBlogNumberOfRating(NumberOfRating);
                            sql = "update Blog\n"
                                    + "set BlogRatingPoint = ?\n"
                                    + "where BlogID = ?";
                            pst = cn.prepareStatement(sql);
                            pst.setInt(1, AvgRatingPoint);
                            pst.setInt(2, blog.getBlogId());
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

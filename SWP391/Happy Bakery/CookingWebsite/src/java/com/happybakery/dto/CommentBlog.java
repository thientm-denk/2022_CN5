/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happybakery.dto;

/**
 *
 * @author thinh
 */
public class CommentBlog {
    private int commentId;
    private int ratedBlogId;
    private int userId;
    private String userName;
    private String userImg;
    private String cmtDescription;
    private int ratingPoint;

    public CommentBlog() {
    }

    public CommentBlog(int commentId, int ratedBlogId, int userId, String userName, String userImg, String cmtDescription, int ratingPoint) {
        this.commentId = commentId;
        this.ratedBlogId = ratedBlogId;
        this.userId = userId;
        this.userName = userName;
        this.userImg = userImg;
        this.cmtDescription = cmtDescription;
        this.ratingPoint = ratingPoint;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getRatedBlogId() {
        return ratedBlogId;
    }

    public void setRatedBlogId(int ratedBlogId) {
        this.ratedBlogId = ratedBlogId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getCmtDescription() {
        return cmtDescription;
    }

    public void setCmtDescription(String cmtDescription) {
        this.cmtDescription = cmtDescription;
    }

    public int getRatingPoint() {
        return ratingPoint;
    }

    public void setRatingPoint(int ratingPoint) {
        this.ratingPoint = ratingPoint;
    }
    
    
}

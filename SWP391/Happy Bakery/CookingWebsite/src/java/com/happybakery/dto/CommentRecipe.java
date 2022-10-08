/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happybakery.dto;

/**
 *
 * @author thinh
 */
public class CommentRecipe {
    private int commentId;
    private int ratedRecipeId;
    private int userId;
    private String userName;
    private String userImg;
    private String cmtDescription;
    private int ratingPoint;

    public CommentRecipe() {
    }

    public CommentRecipe(int commentId, int ratedRecipeId, int userId, String userName, String userImg, String cmtDescription, int ratingPoint) {
        this.commentId = commentId;
        this.ratedRecipeId = ratedRecipeId;
        this.userId = userId;
        this.userName = userName;
        this.userImg = userImg;
        this.cmtDescription = cmtDescription;
        this.ratingPoint = ratingPoint;
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
    
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getRatedRecipeId() {
        return ratedRecipeId;
    }

    public void setRatedRecipeId(int ratedRecipeId) {
        this.ratedRecipeId = ratedRecipeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

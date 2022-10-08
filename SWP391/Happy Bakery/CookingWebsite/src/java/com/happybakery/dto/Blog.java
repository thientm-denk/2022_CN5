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
public class Blog {
    private int blogId;
    private int userId;
    private String userName;
    private String blogImg;
    private String blogAddedDay;
    private String blogTitle;
    private String blogNote;
    private String blogDescription;
    private String blogCategory;
    private int blogRatingPoint;
    private int blogNumberOfRating;

    public Blog() {
    }

    public Blog(int blogId, int userId, String userName, String blogImg, String blogAddedDay, String blogTitle, String blogNote, String blogDescription, String blogCategory, int blogRatingPoint, int blogNumberOfRating) {
        this.blogId = blogId;
        this.userId = userId;
        this.userName = userName;
        this.blogImg = blogImg;
        this.blogAddedDay = blogAddedDay;
        this.blogTitle = blogTitle;
        this.blogNote = blogNote;
        this.blogDescription = blogDescription;
        this.blogCategory = blogCategory;
        this.blogRatingPoint = blogRatingPoint;
        this.blogNumberOfRating = blogNumberOfRating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }    

    public int getBlogRatingPoint() {
        return blogRatingPoint;
    }

    public void setBlogRatingPoint(int blogRatingPoint) {
        this.blogRatingPoint = blogRatingPoint;
    }

    public int getBlogNumberOfRating() {
        return blogNumberOfRating;
    }

    public void setBlogNumberOfRating(int blogNumberOfRating) {
        this.blogNumberOfRating = blogNumberOfRating;
    }

    public String getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(String blogCategory) {
        this.blogCategory = blogCategory;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBlogImg() {
        return blogImg;
    }

    public void setBlogImg(String blogImg) {
        this.blogImg = blogImg;
    }

    public String getBlogAddedDay() {
        return blogAddedDay;
    }

    public void setBlogAddedDay(String blogAddedDay) {
        this.blogAddedDay = blogAddedDay;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogNote() {
        return blogNote;
    }

    public void setBlogNote(String blogNote) {
        this.blogNote = blogNote;
    }

    public String getBlogDescription() {
        return blogDescription;
    }

    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }
    
    
}

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
public class Recipe {
    private int recipeId;
    private int userId;
    private String userName;
    private String recipeName;
    private String recipeDesciption;
    private String recipeAddedDate;
    private int recipeRating;
    private int prepTime;
    private int cookTime;
    private int yields;
    private String recipeImg;
    private String categoryName;
    private String countryName;
    private int numberOfRatings;

    public Recipe() {
    }

    public Recipe(int recipeId, int userId, String userName, String recipeName, String recipeDesciption, String recipeAddedDate, int recipeRating, int prepTime, int cookTime, int yields, String recipeImg, String categoryName, String countryName, int numberOfRatings) {
        this.recipeId = recipeId;
        this.userId = userId;
        this.userName = userName;
        this.recipeName = recipeName;
        this.recipeDesciption = recipeDesciption;
        this.recipeAddedDate = recipeAddedDate;
        this.recipeRating = recipeRating;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.yields = yields;
        this.recipeImg = recipeImg;
        this.categoryName = categoryName;
        this.countryName = countryName;
        this.numberOfRatings = numberOfRatings;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDesciption() {
        return recipeDesciption;
    }

    public void setRecipeDesciption(String recipeDesciption) {
        this.recipeDesciption = recipeDesciption;
    }

    public String getRecipeAddedDate() {
        return recipeAddedDate;
    }

    public void setRecipeAddedDate(String recipeAddedDate) {
        this.recipeAddedDate = recipeAddedDate;
    }

    public int getRecipeRating() {
        return recipeRating;
    }

    public void setRecipeRating(int recipeRating) {
        this.recipeRating = recipeRating;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public int getYields() {
        return yields;
    }

    public void setYields(int yields) {
        this.yields = yields;
    }

    public String getRecipeImg() {
        return recipeImg;
    }

    public void setRecipeImg(String recipeImg) {
        this.recipeImg = recipeImg;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happybakery.dto;

/**
 *
 * @author thinh
 */
public class Ingredient {
    private int recipeId;
    private int ingredientId;
    private String ingredientName;

    public Ingredient() {
    }

    public Ingredient(int recipeId, int ingredientId, String ingredientName) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
    
}

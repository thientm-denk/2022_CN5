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
public class SpecIngredient {
    private int ingredientId;
    private String ingredientName;
    private int storeId;
    private int quantity;
    private String ingredientImg;
    private int price;
    private int salePercent;
    private String ingredientCategory;
    private String ingredientAddedDay;
    

    public SpecIngredient() {
    }

    public SpecIngredient(int ingredientId, String ingredientName, int storeId, int quantity, String ingredientImg, int price, int salePercent, String ingredientCategory, String ingredientAddedDay) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.storeId = storeId;
        this.quantity = quantity;
        this.ingredientImg = ingredientImg;
        this.price = price;
        this.salePercent = salePercent;
        this.ingredientCategory = ingredientCategory;
        this.ingredientAddedDay = ingredientAddedDay;
    }

  
    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

        public String getIngredientImg() {
        return ingredientImg;
    }

    public void setIngredientImg(String ingredientImg) {
        this.ingredientImg = ingredientImg;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(int salePercent) {
        this.salePercent = salePercent;
    }

    public String getIngredientCategory() {
        return ingredientCategory;
    }

    public void setIngredientCategory(String ingredientCategory) {
        this.ingredientCategory = ingredientCategory;
    }

    public String getIngredientAddedDay() {
        return ingredientAddedDay;
    }

    public void setIngredientAddedDay(String ingredientAddedDay) {
        this.ingredientAddedDay = ingredientAddedDay;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happybakery.dao;

import com.happybakery.dto.Ingredient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author thinh
 */
public class IngredientDAO {

    public static ArrayList<Ingredient> getAllIngredientsById(int recipeId) throws Exception {
        ArrayList<Ingredient> list = new ArrayList<>();
        //step 1: tạo connection 
        Connection cn = com.happybakery.mylib.DBUtils.makeConnection();
        if (cn != null) {
            //Step 2: viết sql 
            String sql = "select Ingredient.IngredientID as 'IngredientID', IngredientName\n"
                    + "from Ingredient\n"
                    + "join IngredientUsed on Ingredient.IngredientID = IngredientUsed.IngredientID\n"
                    + "join Recipe on Recipe.RecipeID = IngredientUsed.RecipeID\n"
                    + "where Recipe.RecipeID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, recipeId);
            ResultSet rs = pst.executeQuery();
            //Step 3: xử lý kết quả step2 
            if (rs != null) {
                while (rs.next()) {
                    int IngredientID = rs.getInt("IngredientID");
                    String IngredientName = rs.getString("IngredientName");
                    Ingredient ingredient = new Ingredient(recipeId, IngredientID, IngredientName);
                    list.add(ingredient);
                }// hết while 
            }//hết if
            cn.close();
        }
        return list;
    }
}

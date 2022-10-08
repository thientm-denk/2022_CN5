/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happybakery.dao;

import com.happybakery.dto.SpecIngredient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author thinh
 */
public class SpecIngredientDAO {

    public static ArrayList<SpecIngredient> getIngredientsById(int userId) throws Exception {
        ArrayList<SpecIngredient> list = new ArrayList<>();
        //step 1: tạo connection 
        Connection cn = com.happybakery.mylib.DBUtils.makeConnection();
        if (cn != null) {
            //Step 2: viết sql 
            String sql = "select sa1.IngredientID as 'IngredientID', IngredientName, sa1.StoreID as 'StoreID', sa1.Quantity as 'Quantity', sa1.IngredientImg as 'Image', sa1.Price as 'Price', \n"
                    + "sa1.SalePercentage as 'SalePercent', IngredientCategoryName as 'CategoryName', \n"
                    + "sa1.IngredientAddedDay as 'AddedDay'\n"
                    + "from StoreAvailabe sa1\n"
                    + "join Ingredient on sa1.IngredientID = Ingredient.IngredientID\n"
                    + "join IngredientCategory on sa1.IngredientCategoryID = IngredientCategory.IngredientCategoryID\n"
                    + "where sa1.StoreID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            //Step 3: xử lý kết quả step2 
            if (rs != null) {
                while (rs.next()) {
                    int IngredientID = rs.getInt("IngredientID");
                    String IngredientName = rs.getString("IngredientName");
                    int Quantity = rs.getInt("Quantity");
                    String Image = rs.getString("Image");
                    int Price = rs.getInt("Price");
                    int SalePercent = rs.getInt("SalePercent");
                    String CategoryName = rs.getString("CategoryName");
                    String AddedDay = rs.getString("AddedDay");
                    SpecIngredient specIngredient = new SpecIngredient(IngredientID, IngredientName, userId, Quantity, IngredientName, Price, SalePercent, IngredientName, IngredientName);
                    list.add(specIngredient);
                }// hết while 
            }//hết if
            cn.close();
        }
        return list;
    }
}

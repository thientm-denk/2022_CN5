/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happybakery.dao;

import com.happybakery.dto.Step;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.happybakery.mylib.DBUtils;

/**
 *
 * @author thinh
 */
public class StepDAO {

    public static ArrayList<Step> getAllStepsById(int recipeId) throws Exception {
        ArrayList<Step> list = new ArrayList<>();
        //step 1: tạo connection 
        Connection cn = com.happybakery.mylib.DBUtils.makeConnection();
        if (cn != null) {
            //Step 2: viết sql 
            String sql = "select StepID, StepDescription\n"
                    + "from Step\n"
                    + "where RecipeID = ?\n"
                    + "order by StepID desc";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, recipeId);
            ResultSet rs = pst.executeQuery();
            //Step 3: xử lý kết quả step2 
            int stepPos = 0;
            if (rs != null) {
                while (rs.next()) {
                    stepPos++;
                    int StepID = rs.getInt("StepID");
                    String StepDescription = rs.getString("StepDescription");
                    Step step = new Step(recipeId, stepPos, StepID, StepDescription);
                    list.add(step);
                }// hết while 
            }//hết if
            cn.close();
        }
        return list;
    }
}

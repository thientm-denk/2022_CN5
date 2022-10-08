/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.happybakery.dto;

/**
 *
 * @author thinh
 */
public class Step {

    private int recipeId;
    private int stepPosition;
    private int stepId;
    private String stepDescription;

    public Step() {
    }

    public Step(int recipeId, int stepPosition, int stepId, String stepDescription) {
        this.recipeId = recipeId;
        this.stepPosition = stepPosition;
        this.stepId = stepId;
        this.stepDescription = stepDescription;
    }

    public int getStepPosition() {
        return stepPosition;
    }

    public void setStepPosition(int stepPosition) {
        this.stepPosition = stepPosition;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

}

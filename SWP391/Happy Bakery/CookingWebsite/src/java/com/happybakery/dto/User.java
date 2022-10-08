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
public class User {
    private int userId;
    private String userName;
    private String userMail;
    private String userPassword;
    private String userPhone;
    private String userBDay;
    private String userAddress;
    private String userGender;
    private String userType;
    private int userStatus;
    private String userImg;

    public User() {
    }

    public User(int userId, String userName, String userMail, String userPassword, String userPhone, String userBDay, String userAddress, String userGender, String userType, int userStatus, String userImg) {
        this.userId = userId;
        this.userName = userName;
        this.userMail = userMail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userBDay = userBDay;
        this.userAddress = userAddress;
        this.userGender = userGender;
        this.userType = userType;
        this.userStatus = userStatus;
        this.userImg = userImg;
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

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserBDay() {
        return userBDay;
    }

    public void setUserBDay(String userBDay) {
        this.userBDay = userBDay;
    }

        public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
    
}

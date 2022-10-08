/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.happybakery.dao;

import com.happybakery.dto.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.happybakery.mylib.DBUtils;

/**
 *
 * @author thinh
 */
public class OrderDAO {

    public static ArrayList<Order> getAllOrdersUser(int userId) throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select OrderID, OrderDate, ShipDate, OrderStatus, Users.UserID as 'UserID', Users.UserName as 'UserName', Users.UserType as 'UserType'\n"
                    + "from Users join Orders on Users.UserID = Orders.UserID\n"
                    + "where Users.UserID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, userId);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int orderID = table.getInt("OrderID");
                    String orderDate = table.getString("OrderDate");
                    String shipDate = table.getString("ShipDate");
                    int orderStatus = table.getInt("OrderStatus");
                    int userID = table.getInt("UserID");
                    String UserName = table.getString("UserName");
                    String UserType = table.getString("UserType");
                    Order order = new Order("", orderID, orderDate, shipDate, orderStatus, userID, UserName, UserType);
                    list.add(order);
                }
            }
            cn.close();
        }
        return list;
    }

    public static ArrayList<Order> getAllOrdersStore(int storeId) throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select Orders.OrderID, OrderDate, ShipDate, OrderStatus, Users.UserID as 'UserID', Users.UserName, Users.UserType, COUNT(Users.UserID)\n"
                    + "from StoreAvailabe \n"
                    + "join OrderDetail on StoreAvailabe.SpecificAvailID = OrderDetail.SpecificAvailID \n"
                    + "join Orders on Orders.OrderID = OrderDetail.OrderID \n"
                    + "join Users on Users.UserID = Orders.UserID\n"
                    + "where StoreAvailabe.StoreID = ?\n"
                    + "group by Orders.OrderID, OrderDate, ShipDate, OrderStatus, Users.UserID, Users.UserName, Users.UserType";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, storeId);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int orderID = table.getInt("OrderID");
                    String orderDate = table.getString("OrderDate");
                    String shipDate = table.getString("ShipDate");
                    int orderStatus = table.getInt("OrderStatus");
                    int userID = table.getInt("UserID");
                    String UserName = table.getString("UserName");
                    String UserType = table.getString("UserType");
                    Order order = new Order("", orderID, orderDate, shipDate, orderStatus, userID, UserName, UserType);
                    list.add(order);
                }
            }
            cn.close();
        }
        return list;
    }

    public static ArrayList<Order> getOrdersStoreByStatus(int storeId, int orderStatus) throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select Orders.OrderID, OrderDate, ShipDate, OrderStatus, Users.UserID as 'UserID', Users.UserName, Users.UserType, COUNT(Users.UserID)\n"
                    + "from StoreAvailabe \n"
                    + "join OrderDetail on StoreAvailabe.SpecificAvailID = OrderDetail.SpecificAvailID \n"
                    + "join Orders on Orders.OrderID = OrderDetail.OrderID \n"
                    + "join Users on Users.UserID = Orders.UserID\n"
                    + "where StoreAvailabe.StoreID = ? and OrderStatus = ?\n"
                    + "group by Orders.OrderID, OrderDate, ShipDate, OrderStatus, Users.UserID, Users.UserName, Users.UserType";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, storeId);
            pst.setInt(2, orderStatus);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int orderID = table.getInt("OrderID");
                    String orderDate = table.getString("OrderDate");
                    String shipDate = table.getString("ShipDate");
                    int userID = table.getInt("UserID");
                    String UserName = table.getString("UserName");
                    String UserType = table.getString("UserType");
                    Order order = new Order("", orderID, orderDate, shipDate, orderStatus, userID, UserName, UserType);
                    list.add(order);
                }
            }
            cn.close();
        }
        return list;
    }
    
    public static ArrayList<Order> getOrdersUserByStatus(int userId, int orderStatus) throws Exception {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select OrderID, OrderDate, ShipDate, OrderStatus, Users.UserID as 'UserID', Users.UserName as 'UserName', Users.UserType as 'UserType'\n"
                    + "from Users join Orders on Users.UserID = Orders.UserID\n"
                    + "where Users.UserID = ? and OrderStatus = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, userId);
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                    int orderID = table.getInt("OrderID");
                    String orderDate = table.getString("OrderDate");
                    String shipDate = table.getString("ShipDate");
                    int userID = table.getInt("UserID");
                    String UserName = table.getString("UserName");
                    String UserType = table.getString("UserType");
                    Order order = new Order("", orderID, orderDate, shipDate, orderStatus, userID, UserName, UserType);
                    list.add(order);
                }
            }
            cn.close();
        }
        return list;
    }
}

package com.example.minidictionary;

import java.sql.*;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String databaseUrl="jdbc:mysql://localhost:3306/Dictionary";
    private static final String userName="root";
    private static final String password="manjeet123";

    public Statement getStatement(){
        Statement statement = null;
        Connection conn;
        try{
            conn= DriverManager.getConnection(databaseUrl, userName, password);
            statement = conn.createStatement();
        } catch(Exception e){
            e.printStackTrace();
        }
        return statement;
    }

    public ResultSet getQueryTable(String query){
        Statement statement = getStatement();
        try{
            return statement.executeQuery(query);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args){
//        DatabaseConnection databaseConnection = new DatabaseConnection();
//        ResultSet rs= databaseConnection.getQueryTable("meaning FROM Dictionary.data where word= '%s'");
//        try{
//            while(rs.next()){
//                System.out.println(rs.getString("word")+ " "+ rs.getString("meaning"));
//            }
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//    }


    public int executeUpdateQuery(String query){
        Statement statement = getStatement();
        try{
            return statement.executeUpdate(query);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}

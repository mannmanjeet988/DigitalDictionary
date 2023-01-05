package com.example.minidictionary;

import java.sql.ResultSet;

public class Insertion {

    public static boolean addNewWord(String word, String meaning)
    {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        String query = String.format(("INSERT INTO Dictionary.data (Word, Meaning) VALUES ('%s', '%s')"), word,meaning);
        int rowCount = 0;
        //int rowCount=0;
        try{
            rowCount = databaseConnection.executeUpdateQuery(query);
        } catch(Exception e){
            e.printStackTrace();
        }
      return rowCount!=0;
    }
}

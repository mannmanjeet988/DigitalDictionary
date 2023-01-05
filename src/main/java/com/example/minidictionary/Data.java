package com.example.minidictionary;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.ResultSet;

public class Data extends Pane {

    public SimpleStringProperty word;
    public SimpleStringProperty meaning;
    public SimpleStringProperty synonym;
    public SimpleStringProperty antonym;

    public String  getMeaning() {
        return meaning.get();
    }

    public String getSynonym() {
        return synonym.get();
    }

    public SimpleStringProperty synonymProperty() {
        return synonym;
    }

    public String getAntonym() {
        return antonym.get();
    }

    public SimpleStringProperty antonymProperty() {
        return antonym;
    }

    public String  getWord() {
        return word.get();
    }

    public Data(String word, String meaning, String synonym, String antonym){
        this.word= new SimpleStringProperty(word);
        this.meaning= new SimpleStringProperty(meaning);
        this.synonym= new SimpleStringProperty(synonym);
        this.antonym= new SimpleStringProperty(antonym);
    }

    TextField wordTextField = new TextField();
   // String word= wordTextField.getText();

    public static String getMeaning(String word){
    DatabaseConnection databaseConnection = new DatabaseConnection();
    String ans= null;
        String answer="SELECT * FROM Dictionary.data where Word = 'whole'";
        try{
            ResultSet rs = databaseConnection.getQueryTable(answer);
             ans= String.valueOf(rs);
//            while(rs.next()){
//                System.out.println(rs.getInt("meaning"));
//            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return  ans;
    }

    public static ObservableList<Data> getAllData(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ObservableList<Data> dataList = FXCollections.observableArrayList();
        String selectword="SELECT * FROM Dictionary.data";
        try{
            ResultSet rs = databaseConnection.getQueryTable(selectword);
            while(rs.next()){
                dataList.add(new Data(rs.getString("word"), rs.getString("meaning"),
                                rs.getString("synonym"),
                                rs.getString("antonym")
                        )
                );
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return dataList;
    }

    public static ObservableList<Data> getDataByWord(String requiredWord){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ObservableList<Data> dataList = FXCollections.observableArrayList();
        String selectword= String.format(("SELECT * FROM Dictionary.data WHERE lower(word) like '%%%s%%'"), requiredWord.toLowerCase());
        try{
            ResultSet rs = databaseConnection.getQueryTable(selectword);
            while(rs.next()){
                dataList.add(new Data(rs.getString("word"), rs.getString("meaning"),
                                rs.getString("synonym"),
                                rs.getString("antonym")
                        )
                );
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return dataList;
    }

}

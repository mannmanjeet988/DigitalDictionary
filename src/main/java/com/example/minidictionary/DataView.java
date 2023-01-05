package com.example.minidictionary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.Pane;

import java.sql.ResultSet;

public class DataView {

    public static TableView<Data> dataTable;
    public static Pane getAllData(){
        TableColumn word =  new TableColumn("Word");
        word.setCellValueFactory(new PropertyValueFactory<>("word"));
        TableColumn meaning =  new TableColumn("Meaning");
        meaning.setCellValueFactory(new PropertyValueFactory<>("meaning"));
        TableColumn synonym =  new TableColumn("Synonym");
        synonym.setCellValueFactory(new PropertyValueFactory<>("synonym"));
        TableColumn antonym =  new TableColumn("Antonym");
        antonym.setCellValueFactory(new PropertyValueFactory<>("antonym"));
        //antonym.setCellFactory(new PropertyValueFactory<>("antonym"));

//    ObservableList<Product> data = FXCollections.observableArrayList();
//    data.add(new Product(1,"Lenovo", 8500));
//    data.add(new Product(1,"HP", 9000));

        ObservableList<Data> words=  Data.getAllData();

        dataTable = new TableView<>();
        dataTable.setItems(words);
        dataTable.getColumns().addAll(word,meaning,synonym,antonym);
        dataTable.setMinSize(Dictionary.width,Dictionary.height);

        dataTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);  // to remove the extra column appeared

        Pane tablepane = new Pane();
        tablepane.setStyle("-fx-background-color:#C0C0C0");      //for background color of table
        tablepane.getChildren().add(dataTable);
        tablepane.setMinSize(Dictionary.width,Dictionary.height);
        dataTable.setTranslateY(190);
        return tablepane;
    }

    public static Pane getDataByWord(String requiredWord){
        TableColumn word =  new TableColumn("Word");
        word.setCellValueFactory(new PropertyValueFactory<>("word"));
        TableColumn meaning =  new TableColumn("Meaning");
        meaning.setCellValueFactory(new PropertyValueFactory<>("meaning"));
        TableColumn synonym =  new TableColumn("Synonym");
        synonym.setCellValueFactory(new PropertyValueFactory<>("synonym"));
        TableColumn antonym =  new TableColumn("Antonym");
        antonym.setCellValueFactory(new PropertyValueFactory<>("antonym"));
        //antonym.setCellFactory(new PropertyValueFactory<>("antonym"));

//    ObservableList<Product> data = FXCollections.observableArrayList();
//    data.add(new Product(1,"Lenovo", 8500));
//    data.add(new Product(1,"HP", 9000));

        ObservableList<Data> words=  Data.getDataByWord(requiredWord);

        dataTable = new TableView<>();
        dataTable.setItems(words);
        dataTable.getColumns().addAll(word,meaning,synonym,antonym);
        dataTable.setMinSize(Dictionary.width,300);
        dataTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);  // to remove the extra column appeared
        dataTable.setTranslateY(190);       // to shift table downwards

        Pane tablepane = new Pane();
        //tablepane.setStyle("-fx-background-color:YELLOW");      //for background color of table
        tablepane.getChildren().add(dataTable);
        tablepane.setMinSize(Dictionary.width, Dictionary.height);
        return tablepane;
    }

    public static ObservableList<String> getSuggestionList(String word) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ObservableList<String> suggestionList = FXCollections.observableArrayList();
        // String.format("SELECT * FROM product WHERE lower(name) like '%%%s%%' ", productName.toLowerCase());
        String selectwords = String.format("SELECT word FROM Dictionary.data WHERE lower(Word) like '%%%s%%' ", word.toLowerCase());
        try {
            ResultSet rs = databaseConnection.getQueryTable(selectwords);
            while (rs.next()) {
                suggestionList.add(new String(rs.getString("Word")
                //System.out.println(new String(rs.getString("Word")
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suggestionList;
    }

    public Data getSelectedProduct()
    {
        try{
            Data selectword = dataTable.getSelectionModel().getSelectedItem();
            return selectword;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args){
//        DataView dataview = new DataView();
//        dataview.getSuggestionList("another");
//    }
}

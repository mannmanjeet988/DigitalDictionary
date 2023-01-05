package com.example.minidictionary;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Dictionary extends Application {
    public static final int width=700, height = 600,headerBar = 200;

    ListView<String> suggestionList;
    Pane bodyPane = new Pane();
    static TextField wordTextField;
    static Button searchButton ;
    static Button addButton;
    static Label label = null;

    private GridPane newWordPage(){

        Label newWordLabel = new Label("WORD");
        Label newMeaningLabel = new Label("MEANING");
        Label newSynonymLabel = new Label("SYNONYM");
        Label newAntonymLabel = new Label("ANTONYM");

        TextField newWordTextField = new TextField();
        TextField  newMeaningTextField = new TextField();
        TextField newSynonymTextField = new TextField();
        TextField  newAntonymTextField = new TextField();

         addButton = new Button("Add");
        addButton.setFont(Font.font("ROBOTO", FontWeight.BOLD, 15));
        addButton.setTextFill(Color.DARKBLUE);

        Label headingLabel = new Label("One step Towards Enhancing Vocabulary");
        Label warningLabel1 = new Label("");
        Label warningLabel2 = new Label("");
        Label messageLabel = new Label("");

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String newWord = newWordTextField.getText();
                String newMeaning = newMeaningTextField.getText();
//                if(newWord.isEmpty() || newWord.isBlank()){
//                    warningLabel1.setText("Please enter a word");
//                    if(newMeaning.isEmpty() || newMeaning.isBlank()) {
//                        warningLabel2.setText("Please enter meaning");
//                    }
//                }
                if (Insertion.addNewWord(newWord, newMeaning)) {
                    messageLabel.setText("New Word Added");
                    messageLabel.setTextFill(Color.DARKBLUE);
                }
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
        gridPane.setStyle("-fx-background-color: BEIGE");
        //gridPane.setStyle("-fx-background-color:DIM-BLUE ");
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setAlignment(Pos.CENTER);

        // first is x, second is y
        gridPane.add(newWordLabel,9,3);
        gridPane.add(newWordTextField,10,3);
        gridPane.add(warningLabel1, 11,3);
        gridPane.add(newMeaningLabel,9,5);
        gridPane.add(newMeaningTextField,10,5);
        gridPane.add(warningLabel2, 11,5);
        gridPane.add(newSynonymTextField,10,7);
        gridPane.add(newSynonymLabel,9,7);
        gridPane.add(newAntonymTextField,10,9);
        gridPane.add(newAntonymLabel,9,9)    ;
//        gridPane.add(signUPemailLabel, 10,16);
//        //signUPemailLabel.setFont(new Font("Arial",30));
//        gridPane.add(signUPemailTextField, 11, 16);
//        gridPane.add(signUPpasswordLabel, 10, 18);
//        gridPane.add(signUPpasswordField, 11, 18);
        gridPane.add(addButton,10,11);
        gridPane.add(headingLabel,10,1);
        gridPane.add(messageLabel,11,11);
        newWordLabel.setFont(Font.font("ROBOTO", FontWeight.BOLD, 15));
        newMeaningLabel.setFont(Font.font("ROBOTO", FontWeight.BOLD, 15));
        newSynonymLabel.setFont(Font.font("ROBOTO", FontWeight.BOLD, 15));
        newAntonymLabel.setFont(Font.font("ROBOTO", FontWeight.BOLD, 15));
        newWordLabel.setTextFill(Color.PURPLE);
        newMeaningLabel.setTextFill(Color.PURPLE);
        newSynonymLabel.setTextFill(Color.PURPLE);
        newAntonymLabel.setTextFill(Color.PURPLE);
        headingLabel.setFont(Font.font("ROBOTO", FontWeight.BOLD, 20));
        messageLabel.setFont(Font.font("ROBOTO", FontWeight.BOLD, 13));
        headingLabel.setTextFill(Color.GREEN);

        return gridPane;
    }

    private GridPane HeaderBar(){
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(bodyPane.getMinWidth(),headerBar-10);
      gridPane.setStyle("-fx-background-color: YELLOWGREEN");

        searchButton= new Button("Search");
        searchButton.setFont(Font.font("ROBOTO", FontWeight.BOLD, 15));
        searchButton.setTextFill(Color.DARKBLUE);
        wordTextField = new TextField();
        label = new Label("Type Word To search");
        label.setTextFill(Color.DARKBLUE);
        label.setFont(Font.font("ROBOTO", FontWeight.BOLD, 12));
         Label label2 = new Label("To add new word to the Dictionary,Click on GO");
        label2.setTextFill(Color.PURPLE);

        Button GoButton = new Button("  Go  ");
        GoButton.setFont(Font.font("ROBOTO", FontWeight.BOLD, 15));
        GoButton.setTextFill(Color.DARKBLUE);
        Label suggestionListLabel = new Label("Suggestion List");
        suggestionListLabel.setFont(Font.font("ROBOTO", FontWeight.BOLD, 13));
        suggestionListLabel.setTextFill(Color.DARKBLUE);
        gridPane.setAlignment(Pos.CENTER);

        GoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //wordTextField.setText(null);
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(newWordPage());
            }
        });

        //bodyPane.setTranslateX(100);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//               
                String word = wordTextField.getText();
                if (word.isBlank() || word.isEmpty()) {
                    label.setText("Please enter a word!");
                    label.setTextFill(Color.RED);
                    label.setFont(Font.font("ROBOTO", FontWeight.BOLD, 12));
                } else {
                    label.setText("Type word to search");
                    label.setTextFill(Color.DARKBLUE);
                    //bodyPane.getChildren().clear();
                    bodyPane.getChildren().addAll(DataView.getDataByWord(word));
                }
            }
        });

        ListView<String> suggestionList = new ListView<>();
        suggestionList.setMinSize(330,50);
        suggestionList.setMaxSize(400,50);
        String word = wordTextField.getText();
//        ArrayList<String> WordList= new ArrayList<>();
//         WordList= (ArrayList<String>) DataView.getSuggestionList(word);
        // fetch from a trie or database or list and bind to ListView as below.

        ObservableList<String> WordList = DataView.getSuggestionList(word);

        //String[] WordList =    {"collaborate","whole","aggravate","another"} ;
        suggestionList.getItems().addAll(WordList);
        suggestionList.setOrientation(Orientation.HORIZONTAL);  // for orientation of suggestion list

        suggestionList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String selectedWord = suggestionList.getSelectionModel().getSelectedItem();
                wordTextField.setText(selectedWord);
                // on click find the meaning and display it.
            }
        });

        gridPane.add(wordTextField,2,0);
        gridPane.add(label,2,1);
        gridPane.add(searchButton,3,0);
        gridPane.add(suggestionList,2,4);
        gridPane.add(suggestionListLabel,2,5);
        gridPane.add(label2,2,7);
        gridPane.add(GoButton,3,7);
        label2.setFont(Font.font("ROBOTO", FontWeight.BOLD, 17));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        return gridPane;
    }
    

    Pane createContent(){
        Pane root = new Pane();

        InputStream stream1 = null;
        InputStream stream2 = null;
        try {
            stream1 = new FileInputStream("I:\\MiniDictionary\\src\\main\\resources\\backgorund 2.jpg");
            stream2 = new FileInputStream("I:\\MiniDictionary\\src\\main\\resources\\dic logo.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Image image = new Image(stream1);
        Image image2 = new Image(stream2);
//        BackgroundImage backgroundimage = new BackgroundImage(image,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
//                BackgroundSize.DEFAULT);
//        Background background= new Background(backgroundimage);
        ImageView imageView= new ImageView();
        ImageView logo= new ImageView();
        imageView.setImage(image);
        logo.setImage(image2);

        imageView.setFitHeight(150);
        imageView.setFitWidth(width);
        logo.setFitHeight(150);
        logo.setFitWidth(150);
        imageView.setTranslateY(450);
       // imageView.setTranslateX(550);
        logo.setTranslateY(450);

        root.setPrefSize(width,height);
        bodyPane.setMinSize(width,height);
        bodyPane.setTranslateY(30);
      //  bodyPane.setBackground(background);

      //bodyPane.getChildren().addAll(DataView.getAllData());
 //      bodyPane.getChildren().addAll(DataView.getDataByWord());
        root.getChildren().addAll(bodyPane, HeaderBar(),imageView,logo);

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Dictionary!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
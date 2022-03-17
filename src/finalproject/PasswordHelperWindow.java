/*
Programmer: Brian Dean
Purpose: Final Project Phase 4
IDE Used: NetBeans IDE
Date: 2/26/22
 */
package finalproject;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.sql.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//creating the main class for our Phase 3 GUI Application
public class PasswordHelperWindow extends Application 
{
    //creating all of the required private fields
    private final String URL = "jdbc:derby:FinalProjectDB";
    private Label titleLabel;
    private Label passwordLabel;
    private Label phraseLabel;
    private Label userPhraseLabel;
    private Label digitLabel;
    private Label charLabel;
    private ArrayList<String>  phraseArrayList;
    private ArrayList<Integer>  digitArrayList;
    private ArrayList<String>  charArrayList;
    private ArrayList<String> userPhraseArrayList;
    private ListView<String> phraseListView;
    private ListView<Integer> digitListView;
    private ListView<String> charListView;
    private ListView<String> userPhraseListView;
    private Button getPasswordButton;
    private Button nextPasswordButton;
    private Button exitButton;
    private Button refreshButton;
    private TextField passwordOutputTextField;
    private Integer passwordID;
    private PasswordGame obj = new PasswordGame();
    private String password;
    private String phrase;

    public static void main(String[] args) 
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws SQLException
    {
        //creating the connection to our database
        Connection conn = DriverManager.getConnection(URL);
        
        //creating a statement that allows for scrolling and updating
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        
        //creating the main title label
        titleLabel = new Label("Select A Single Phrase, A Digit, and A Special Character");
        titleLabel.setStyle("-fx-font-size: 13pt; -fx-font-weight: bold; -fx-text-fill: RoyalBlue");

        //creating the array lists for our phrases, digits, and characters
        phraseArrayList = new ArrayList<>();
        digitArrayList = new ArrayList<>();
        charArrayList = new ArrayList<>();
        userPhraseArrayList = new ArrayList<>();
        
        //retrieving the phrases from our database
        ResultSet resultSet = stmt.executeQuery("SELECT SavedPhrase, SavedDigit, SaveChar FROM Phrases");
        
        //sending the cursor to the last row
        resultSet.last();
        
        //assinging the row integer with the amount of rows in our table
        int rowAmount = resultSet.getRow();
        
        //sending the cursor back to the first row
        resultSet.first();
        
        //using a for loop to traverse through the table rows and add the first 10 elements to all three lists
        for(int i = 0; i < 10; i++)
        {
            //adding the phrase element to the list from the first column in the query result
            phraseArrayList.add(resultSet.getString(1));
            
            //adding the digit to its list from the second column
            digitArrayList.add(resultSet.getInt(2));
            
            //adding the character to its list from the third column in the query
            charArrayList.add(resultSet.getString(3));
                    
            //moving the cursor down one spot
            resultSet.next();   
        }
        
        //now using the rowAmount integer to traverse through the remaining phrases in the table, which are
        // the user entered/saved phrases from the password game. 
        for(int i = 10; i < rowAmount; i++)
        {
            //adding the user-defined phrases into a the new userPhrase array list
            userPhraseArrayList.add(resultSet.getString(1));
            
            //moving the cursor down one spot
            resultSet.next();
        }
        
        //creating the phrase, digit, character, and userPhrase observable list objects from the array lists
        ObservableList<String> phraseList = FXCollections.observableArrayList(phraseArrayList);
        ObservableList<Integer> digitList = FXCollections.observableArrayList(digitArrayList);
        ObservableList<String> charList = FXCollections.observableArrayList(charArrayList);
        ObservableList<String> userPhraseList = FXCollections.observableArrayList(userPhraseArrayList);
        
        //now using the observable list objects to create and fill the list view controls
        phraseListView = new ListView<>(phraseList);
        digitListView = new ListView<>(digitList);
        charListView = new ListView<>(charList);
        userPhraseListView = new ListView<>(userPhraseList);
        
        //setting the sizes of our list view objects
        phraseListView.setPrefSize(200, 250);
        digitListView.setPrefSize(30, 250);
        charListView.setPrefSize(30, 250);
        userPhraseListView.setPrefSize(200,250);
        
        //creating our phrase, digit and character labels
        phraseLabel = new Label("Pre-Defined Phrases: ");
        phraseLabel.setStyle("-fx-font-size: 8pt; -fx-font-weight: bold; -fx-text-fill: SlateGrey");
        userPhraseLabel = new Label("User-Entered Phrases: ");
        userPhraseLabel.setStyle("-fx-font-size: 8pt; -fx-font-weight: bold; -fx-text-fill: SlateGrey");
        digitLabel = new Label("Digit: ");
        digitLabel.setStyle("-fx-font-size: 8pt; -fx-font-weight: bold; -fx-text-fill: SlateGrey");
        charLabel = new Label("Character: ");
        charLabel.setStyle("-fx-font-size: 8pt; -fx-font-weight: bold; -fx-text-fill: SlateGrey");
        
        //adding the phrase label and list view into a VBox
        VBox predefinedBox = new VBox(10, phraseLabel, phraseListView);
        predefinedBox.setAlignment(Pos.TOP_LEFT);
        predefinedBox.setPadding(new Insets(10));
        
        //adding the user phrase label and list view into a VBox
        VBox userBox = new VBox(10, userPhraseLabel, userPhraseListView);
        userBox.setAlignment(Pos.TOP_LEFT);
        userBox.setPadding(new Insets(10));
        
        //adding the digit label and listview into a VBox
        VBox digitBox = new VBox(10, digitLabel, digitListView);
        digitBox.setAlignment(Pos.TOP_LEFT);
        digitBox.setPadding(new Insets(10));
        
        //adding the char label and listivew into a VBox
        VBox charBox = new VBox(10, charLabel, charListView);
        charBox.setAlignment(Pos.TOP_LEFT);
        charBox.setPadding(new Insets(10));
        
        //adding all of the recently created VBoxes into a single HBox
        HBox listViewBox = new HBox(15, predefinedBox, userBox, digitBox, charBox);
        listViewBox.setAlignment(Pos.CENTER);
        listViewBox.setPadding(new Insets(10));
        
        //creating the get password button and text field for the password output
        getPasswordButton = new Button("Get Password");
        passwordOutputTextField = new TextField();
        passwordOutputTextField.setStyle("-fx-font-size: 10pt");
        getPasswordButton.setStyle("-fx-font-size: 10pt; -fx-text-fill: Indigo");
        
        //putting the password button and output text field into a VBox
        VBox passwordBox = new VBox(10, getPasswordButton, passwordOutputTextField);
        passwordBox.setAlignment(Pos.CENTER);
        passwordBox.setPadding(new Insets(10));
        
        //creating the password number label
        passwordLabel = new Label("Password ID: ");
        passwordLabel.setStyle("-fx-font-size: 10pt; -fx-text-fill: Green");
        
        //creating the next password button
        nextPasswordButton = new Button("Next Password");
        nextPasswordButton.setStyle("-fx-font-size: 10pt; -fx-text-fill: Indigo");
        
        //setting the event of the next button
        nextPasswordButton.setOnAction(event->
        {
            //resetting the password number label and password output text field
            passwordLabel.setText("Password ID: ");
            passwordOutputTextField.setText("");
            
        });
        
        //putting the password label and next button into an HBox
        HBox passwordBox2 = new HBox(40, passwordLabel, nextPasswordButton);
        passwordBox2.setAlignment(Pos.CENTER);
        passwordBox2.setPadding(new Insets(10));
        
        //registering the get password button to an event handler
        getPasswordButton.setOnAction(event->
        {      
            //if statement that 
            if(phraseListView.getSelectionModel().isEmpty())
            {
                phrase = userPhraseListView.getSelectionModel().getSelectedItem();
            }
            
            else if(userPhraseListView.getSelectionModel().isEmpty())
            {
                phrase = phraseListView.getSelectionModel().getSelectedItem();
            }
            
            //getting the phrase, digit and characters selected by the user
            Integer digit = digitListView.getSelectionModel().getSelectedItem();
            String character = charListView.getSelectionModel().getSelectedItem();
            
            //combining the options selected by the user into one single string variable
            String temp = phrase + digit.toString() + character;
            
            //while we are not getting the string response, we will use the getLengthResponse
            // in order to set the phrase of the PasswordCreator object and receive an encrypted password
            String eatOutput = obj.getLengthResponse(temp);
            
            //getting the password
            password = obj.getPassword();
            
            //now setting the password output text field equal to the newly created password
            passwordOutputTextField.setText(password);
            
            //try catch block to handle the SQLException if thrown
            try
            {
                //creating a ResultSet to gather the contents of the password table
                ResultSet passwordResultSet = stmt.executeQuery("SELECT Password FROM Passwords");
                
                //finding the number of total rows in the table
                passwordResultSet.last();
                passwordID = passwordResultSet.getRow() + 1;

                //inserting the new password into the password table
                stmt.executeUpdate("INSERT INTO Passwords VALUES"
                        + "(" + passwordID.toString() + ", '" + password + "')");   
            }
            
            //catch clause for the SQLException
            catch(SQLException e)
            {
                System.out.print(e.getMessage());
            }

            //updating the password number and label
            passwordLabel.setText("Password ID: " + passwordID.toString());
            
            //resetting the selections for each list view
            phraseListView.getSelectionModel().clearSelection();
            userPhraseListView.getSelectionModel().clearSelection();
            digitListView.getSelectionModel().clearSelection();
            charListView.getSelectionModel().clearSelection();
            
        });
        
        //creating the exit button
        exitButton = new Button("Exit");
        exitButton.setStyle("-fx-font-size: 10pt; -fx-text-fill: firebrick");
        
        //creating the exit button event handler method
        exitButton.setOnAction(event->
        {
            //try catch block to handle any SQLException thrown 
            try
            {               
                //closing the statement and connection to the database
                stmt.close();
                conn.close();
                
                //closing the application
                primaryStage.close();
            }  
            
            catch(SQLException e)
            {
                System.out.print(e.getMessage());
            }
            
        });
        
        //creating the refresh button
        refreshButton = new Button("Refresh");
        refreshButton.setStyle("-fx-font-size: 10pt; -fx-text-fill: indigo");
        
        //creating the refresh button event handler
        refreshButton.setOnAction(event->
        {
            //try catch block to handle any SQLException thrown 
            try
            {
                //closing the application
                primaryStage.close();
                
                //running a SQL select statement that retrieves only the user entered phrases from the phrase table
                ResultSet refreshSet = stmt.executeQuery("SELECT SavedPhrase FROM Phrases WHERE PhraseID > 10");
                
                //getting the amount of phrases found in our query
                refreshSet.last();
                int rows = refreshSet.getRow();
                
                //sending the cursor back to the first row
                refreshSet.first();
                
                //clearing the user phrase array list
                userPhraseArrayList.clear();

                //using a for loop to re-populate the array list with our updated phrase list
                for(int i = 0; i < rows; i++)
                {
                    //adding the phrase into the list
                    userPhraseArrayList.add(refreshSet.getString(1));
                    
                    //moving the cursor forward one row
                    refreshSet.next();
                }
                
                //creating a new observable list object with our updated array list
                ObservableList<String> refreshList = FXCollections.observableArrayList(userPhraseArrayList);
                
                //re-creating the list view object that is populated with the refreshed phrases
                userPhraseListView = new ListView<>(refreshList);
                
                //refreshing the listview object
                userPhraseListView.refresh();
                
                //calling the start method using the stage object, so as it to restart the GUI Application 
                start(primaryStage);
            }
            
            //catching the SQLException
            catch(SQLException e)
            {
                System.out.print(e.getMessage());
            }
                
        });
        
        //putting the refresh and exit buttons into an HBox
        HBox exitRefreshBox = new HBox(500, exitButton, refreshButton);
        exitRefreshBox.setAlignment(Pos.CENTER);
        exitRefreshBox.setPadding(new Insets(10));       
        
        //adding all of the controls into a final VBox
        VBox finalBox = new VBox(10, titleLabel, listViewBox, passwordBox, passwordBox2, exitRefreshBox);
        finalBox.setAlignment(Pos.CENTER);
        finalBox.setPadding(new Insets(10));
          
        //creating the scene object
        Scene scene = new Scene(finalBox);
        
        //adding the scene to the stage
        primaryStage.setScene(scene);
        
        //adding a title to the window
        primaryStage.setTitle("Password Helper");
        
        //showing the stage on screen
        primaryStage.show();
    }  
}

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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.sql.*;

//creating the main class that will create our GUI application window
public class FinalProject extends Application 
{
    //creating all of the necessary private fields
    private Label titleLabel;
    private Label userScoreLabel;
    private Label hackerScoreLabel;
    private Label enterPhraseLabel;
    private Label lengthResponseLabel;
    private Label questionLabel;
    private Label passwordHelperLabel;
    private Image userImage;
    private ImageView userImageView;
    private Image hackerImage;
    private ImageView hackerImageView;
    private Button checkLengthButton;
    private Button getPasswordButton;
    private Button resetButton;
    private Button openPasswordHelperButton;
    private Button exitButton;
    private RadioButton copyPasteChoice;
    private RadioButton writeDownChoice;
    private TextField userInputField;
    private TextField passwordOutputTextField;
    private String userInput;
    private String password;
    private PasswordGame obj = new PasswordGame();
    private final String URL = "jdbc:derby:FinalProjectDB";
    private Integer phraseID;
    private Integer passwordID;
   
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    //overriding the start method and notifying that it can throw the SQLException
    @Override
    public void start(Stage primaryStage) throws SQLException
    {
        //creating the connection object
        Connection conn = DriverManager.getConnection(URL);
        
        //creating the statement object that is updateable and allows for scrolling
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    
        //creating our result set object with a SQL statement retrieving the phrase ID's from the phrase table
        ResultSet resultSet = stmt.executeQuery("SELECT PhraseID FROM Phrases");
        
        //getting the number of rows in our Phrases table in our Final Project Database
        resultSet.last();
                    
        //incrementing the row variable by one and using it as the Phrase ID for the saved phrase that will be inserted
        phraseID  = resultSet.getRow() + 1;
         
        //creating and setting the style for the main title label
        titleLabel = new Label("Password Creator Game");
        titleLabel.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: MediumSeaGreen");
        
        //creating each of the user and hacker images from our saved, imported images
        hackerImage = new Image("file:hacker.jpg");
        userImage = new Image("file:user.jpg");
        hackerImageView = new ImageView(hackerImage);
        userImageView = new ImageView(userImage);
        
        //setting the dimensions and preserving the ratios for each image view object
        hackerImageView.setFitHeight(150);
        hackerImageView.setFitWidth(150);
        hackerImageView.setPreserveRatio(true);
        userImageView.setFitHeight(150);
        userImageView.setFitWidth(150);
        userImageView.setPreserveRatio(true);
        
        //creating the user and hacker score labels
        userScoreLabel = new Label("User Score: 0");
        userScoreLabel.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: RoyalBlue");
        hackerScoreLabel = new Label("Hacker Score: 0");
        hackerScoreLabel.setStyle("-fx-font-size: 15pt; -fx-font-weight: bold; -fx-text-fill: Tomato");
        
        //placing each user/hacker label and image into their own separate VBoxes
        VBox userBox = new VBox(10, userImageView, userScoreLabel);
        userBox.setAlignment(Pos.CENTER);
        userBox.setPadding(new Insets(10));
        VBox hackerBox = new VBox(10, hackerImageView, hackerScoreLabel);
        hackerBox.setAlignment(Pos.CENTER);
        hackerBox.setPadding(new Insets(10));
        
        //creating an hbox that holds the title and two user/hacker boxes
        HBox topTitleBox = new HBox(10, userBox, titleLabel, hackerBox);
        topTitleBox.setAlignment(Pos.TOP_CENTER);
        topTitleBox.setPadding(new Insets(10));
        
        //creating the prompt label
        enterPhraseLabel = new Label("Enter A Favorite Phrase (Minimum Length: 15 Characters)");
        enterPhraseLabel.setStyle("-fx-font-size: 12pt; -fx-font-weight: bold; -fx-text-fill: SlateGrey");
        
        //creating the text field to allow us to receive user input
        userInputField = new TextField();
        
        //adding the enterPhraseLabel and text field into another VBox
        VBox inputBox = new VBox(10, enterPhraseLabel, userInputField);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPadding(new Insets(10));
        
        //creating the check length label and button
        lengthResponseLabel = new Label();
        checkLengthButton = new Button("Check Length");
        lengthResponseLabel.setStyle("-fx-font-size: 10pt");
        checkLengthButton.setStyle("-fx-font-size: 10pt; -fx-text-fill: Indigo");
        
        //registering the check length button to an event
        checkLengthButton.setOnAction(event -> 
        {
            //if statement that tells the user that they have not entered anything to check the length of
            if(userInputField.getText().length() == 0)
            {
                lengthResponseLabel.setText("Text Field Is Blank.");
            }
            
            //an else if statement that will not allow the user to click the check length button numerous 
            // times and continue to add points to their score without changing their entered phrase
            else if(!(userInputField.getText().equals(userInput)))
            {
                //first, we are getting the string object entered by the user in the text field
                userInput = userInputField.getText();

                //printing the result from the PasswordGame's getLengthResponse method
                lengthResponseLabel.setText(obj.getLengthResponse(userInput));
            
                //using the Integer wrapper class to get the updated scores and set their respective labels to equal them
                Integer userScore = obj.getUserScore();
                userScoreLabel.setText("User Score: " + userScore.toString());
                Integer hackerScore = obj.getHackerScore();
                hackerScoreLabel.setText("Hacker Score: " + hackerScore.toString());
            }   
            
            //resetting the password text field, so it is blank if the user tries to check the length of another phrase
            passwordOutputTextField.setText(""); 
            
        });
        
        //adding the check length button and its label into a HBox
        HBox lengthBox = new HBox(10, checkLengthButton, lengthResponseLabel);
        lengthBox.setAlignment(Pos.CENTER);
        lengthBox.setPadding(new Insets(10));
        
        //creating the password output text field and get password button
        passwordOutputTextField = new TextField();
        getPasswordButton = new Button("Get Password");
        passwordOutputTextField.setStyle("-fx-font-size: 10pt");
        getPasswordButton.setStyle("-fx-font-size: 10pt; -fx-text-fill: Indigo");
        
        //registering the get password button to its event handler
        getPasswordButton.setOnAction(event->
        {
            //using an if statement that will check if the user has entered an acceptable phrase in the text field
            if(lengthResponseLabel.getText().equals("Good!"))
            {   
                //getting and setting the newly enerated password
                password = obj.getPassword();
                
                //setting the password output text field equal to the newly created password
                passwordOutputTextField.setText(password);
                
                //try-catch clause that will handle any SQLExceptions thrown
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
                
                    //Now adding the phrase entered by the user into our the Phrases table
                    // To fill the SavedDigit and SavedChar attributes, we will use 11 and } for each user entry
                    stmt.executeUpdate("INSERT INTO Phrases VALUES"
                            + "(" + phraseID.toString() + ", '" + userInput + "', 11, '}')"); 
                    
                    //increasing the phraseID by one to allow for the insertion of a unique phrase ID on the next insertion
                    phraseID++;
                
                }
                
                //catching the SQLException
                catch(SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            
        });
        
        //adding the password text field and button into a HBox
        HBox passwordBox = new HBox(10, getPasswordButton, passwordOutputTextField);
        passwordBox.setAlignment(Pos.CENTER_LEFT);
        passwordBox.setPadding(new Insets(10));
        
        //creating another HBox to hold the length and password boxes
        HBox lengthPasswordBox = new HBox(10, lengthBox, passwordBox);
        lengthPasswordBox.setAlignment(Pos.CENTER);
        lengthPasswordBox.setPadding(new Insets(20));
        
        //creating the question label
        questionLabel = new Label("What Will You Do With This Password?");
        questionLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-text-fill: SlateGrey");
        
        //making the two RadioButtons for the choices the user can select from
        copyPasteChoice = new RadioButton("Copy/Paste It To The Password Box Of Your Online Account");
        writeDownChoice = new RadioButton("Write It Down On A Piece Of Paper");
        
        //creating and grouping the Radio Buttons using a ToggleGroup
        ToggleGroup choiceGroup = new ToggleGroup();
        copyPasteChoice.setToggleGroup(choiceGroup);
        writeDownChoice.setToggleGroup(choiceGroup);
        
        //registering the copyPasteChoice radio button to an event handler
        copyPasteChoice.setOnAction(event->
        {
            //if statement that only allows the score to increase if the user has a password created
            if(passwordOutputTextField.getText().length() > 0)
            {
                //updating the user score and updating the user score's label
                obj.updateUserScore();
                Integer userScore = obj.getUserScore();
                userScoreLabel.setText("User Score: " + userScore.toString());
            }
            
        });
        
        //registering the writeDownChoice radio button to an event handler
        writeDownChoice.setOnAction(event->
        {
            //if statement that only allows the score to increase if the user has a password created
            if(passwordOutputTextField.getText().length() > 0)
            {
                //updating the hacker score and label
                obj.updateHackerScore();
                Integer hackerScore = obj.getHackerScore();
                hackerScoreLabel.setText("Hacker Score: " + hackerScore.toString());
            }
            
        });
        
        //adding the question label and choice group buttons into a VBox container
        VBox choiceBox = new VBox(10, questionLabel, copyPasteChoice, writeDownChoice);
        choiceBox.setAlignment(Pos.CENTER_LEFT);
        choiceBox.setPadding(new Insets(10));
        
        //creating the reset button
        resetButton = new Button("Reset");
        resetButton.setStyle("-fx-font-size: 10pt; -fx-text-fill: firebrick");
        
        //registering the reset button to an action event
        resetButton.setOnAction(event->
        {
            //calling the PasswordGame's reset method to set each score back to zero
            obj.reset();
            
            //using the Integer wrapper class to set the score labels back to 0
            Integer userScore = obj.getUserScore();
            userScoreLabel.setText("User Score: " + userScore.toString());
            Integer hackerScore = obj.getHackerScore();
            hackerScoreLabel.setText("Hacker Score: " + hackerScore.toString());
            
            //resetting the check length label and get password text field
            passwordOutputTextField.setText("");
            lengthResponseLabel.setText("");
            
            //emptying the text field
            userInputField.setText("");
            
            //deselecting both of the radio button options
            copyPasteChoice.setSelected(false);
            writeDownChoice.setSelected(false);
        });
        
        //adding the reset button into an hbox         
        HBox resetBox = new HBox(resetButton);
        resetBox.setAlignment(Pos.BASELINE_RIGHT);
        resetBox.setPadding(new Insets(10));
        
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
        
        //adding the exitbutton into an HBox        
        HBox exitBox = new HBox(exitButton);
        exitBox.setAlignment(Pos.BASELINE_LEFT);
        exitBox.setPadding(new Insets(10));
        
        //adding the reset box and exit box into another HBox
        HBox exitRefreshBox = new HBox(500, exitButton, resetButton);
        exitRefreshBox.setAlignment(Pos.CENTER);
        exitRefreshBox.setPadding(new Insets(10));
        
        //creating the label for the password helper
        passwordHelperLabel = new Label("Need Help? Try The Password Helper");
        passwordHelperLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-text-fill: SlateGrey");
        
        //creating the button to open the password helper application
        openPasswordHelperButton = new Button("Open Password Helper");
        openPasswordHelperButton.setStyle("-fx-font-size: 10pt; -fx-text-fill: Indigo");
        
        //registering the password helper button to the an action event
        openPasswordHelperButton.setOnAction(event ->
        {
            //creating a new FinalProjectPhase3 object and a new stage 
            PasswordHelperWindow temp = new PasswordHelperWindow();
            Stage passwordHelperStage = new Stage();
            
            //try-catch block to handle the exceptions thrown
            try
            {
                //calling the start method which will create our second window with the password helper GUI application 
                temp.start(passwordHelperStage);    
            }
            
            //catching the SQLException
            catch(SQLException e)
            {
                System.out.println(e.getMessage());
            }
             
        });
        
        //adding the password helper label and button into an HBox
        HBox passwordHelperBox = new HBox(10, passwordHelperLabel, openPasswordHelperButton);
        passwordHelperBox.setAlignment(Pos.CENTER);
        passwordHelperBox.setPadding(new Insets(10));

        //adding the exitRefresh and password helper boxes into a VBox
        VBox resetExitHelperBox = new VBox(10, passwordHelperBox, exitRefreshBox);
        resetExitHelperBox.setAlignment(Pos.CENTER);
        resetExitHelperBox.setPadding(new Insets(10));
        
        //adding each component into a final VBox
        VBox finalBox = new VBox(10, topTitleBox, inputBox, lengthPasswordBox, choiceBox, resetExitHelperBox);
        finalBox.setAlignment(Pos.CENTER);
        finalBox.setPadding(new Insets(10));
        
        //adding the completed VBox into the scene
        Scene scene = new Scene(finalBox);
        
        //adding the scene to the stage
        primaryStage.setScene(scene);
        
        //setting the title for the window
        primaryStage.setTitle("Password Creator Game");
        
        //showing the stage on the screen
        primaryStage.show();
  
    }
  
}

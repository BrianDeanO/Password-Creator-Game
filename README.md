# Password Creator Game
### Synopsis
This GUI application was made by using controls and containers from the JavaFX library. This password creator game is designed to help users create a strongly encrypted password for and to teach them what to do with said password. The user competes against the computer to create the encrypted password by entering a phrase of a specified length. If they need help, there is an option to open a secondary window that provides saved phrases, characters, and digits that will generate an encrypted password for the user.

##
![Password Creator - Main Window](https://user-images.githubusercontent.com/54780901/159052113-660b4396-db64-4ba6-a52f-6f0be87e134b.png)
![Password Helper - Secondary Window](https://user-images.githubusercontent.com/54780901/159052124-7238a25f-fac8-47de-a585-b5dc64b0aa7e.png)
##

### Opening The Application 
To play the Password Creator Game and open the GUI application, the user runs the FinalProject.java file. This file is the main file for this project and includes the design and controls of the main Password Creator Game window and the implementation of the derived classes that help run the game and create passwords. 

### Earning Points
There are two ways that the user and hacker can receive points. The first way to earn a point is determined by whether or not the user enters a phrase that meets the minimum length requirement of 15 characters. If the phrase length is acceptable, the user gains a point. Otherwise, the hacker receives a point. Once the user enters a good phrase and receives a password, they can select what they will do with the password. This selection is the second way to earn a point in the game. If the user chooses to use the password for their online account, they will receive a point. And if they choose to write the password down, the hacker receives a point. By entering a new phrase into the text field, the user can continue to compete against the computer. And if the user wants to restart the game and reset the scores, pressing the reset button will do just that and clear each text field, box, and selection on the application window.

### Preventive Measures
Several preventative measures within the application help stop the user from exploiting the game. Most importantly, to prevent the user from creating a password with a phrase that does not meet the length requirement, the application will not allow the user to select the Get Password Button or the additional option buttons. If the user presses the Check Length button more than once and their phrase in the text field is the same as before, they will not gain any additional points. And since the only requirement is that the phrase has been checked and is of an acceptable length, the user can receive more than one password from the phrase without changing it in the text field. Also, when choosing an option for what to do with the password, the first selection made by the user is the only one that awards points for that single password. This measure is in place to stop the user from clicking back and forth between the options and awarding infinite points to themselves and the hacker. Another measure in place is for the Password Helper window, and it stops the user from creating a password if they have selected a phrase from both of the lists of phrases.

### Encryption Process
Located within the PasswordCreator.java file is the createPassword function that acts as our phrase encryption algorithm. After converting the chosen or selected phrase string into a character array, the function reverses the order of phrase, inserts a random character on both ends, and inserts a random one-digit integer in each of the phrase’s whitespaces. From the test examples shown below, when the user entered “This Is A Test Password,” the generated password saved to the Passwords table in our database was, “!drowssaP2tseT2A2sI4sihT?”. Found via an online password strength tool, it determined that this password would take a computer 28 nonillion years to crack. 

### Getting Help from the Password Helper Window
From the main Password Creator Game window, the user can select the "Open Password Helper" button, which will cause the secondary Password Helper window to appear on the screen. The design and creation of this window come from the PasswordHelperWindow.java file. And to create a unique password from this window, the user selects one phrase from either the list of pre-defined phrases or the list of phrases previously entered by the user. Then after selecting a digit and a special character, the user can receive their encrypted password.  

### Database Implementation
The creation of our JDBC database that is used to save, store, and retrieve information occurs within the FinalProjectDatabaseCreator.java file. This database contains two tables. The first table, titled Phrases, uses its first ten rows to supply the Password Helper window with pre-defined phrases, characters, and digits. Within the FinalProjectDatabaseFiller.java file are the SQL statements that handle the insertion of these first ten entries. And also inserted into the Phrases table are any user-entered phrases encrypted via the main Password Creator Game window. The second table in the database, labeled Passwords, is where the application saves each of the passwords created in both the Password Creator Game and Password Helper windows. And using SQL select statements executed in the FinalProjectTest.java file, the contents of the two tables are shown below. The three test phrases entered in the Password Creator Game window appear in the phrase table. The passwords encrypted from the user-entered phrases are in the password table, along with an additional password made using the Password Helper.

![image](https://user-images.githubusercontent.com/54780901/159018701-ae607028-c489-4a75-91b6-9013e3b99158.png)

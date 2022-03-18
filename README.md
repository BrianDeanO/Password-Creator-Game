# Password Creator Game
### Synopsis
This GUI application was made by using controls and containers from the JavaFX library. This password creator game is designed to help users create a strongly encrypted password for and to teach them what to do with said password. The user competes against the computer to create the encrypted password by entering a phrase of a specified length. If they need help, there is an option to open a secondary window that provides saved phrases, characters, and digits that will generate an encrypted password for the user.

##
![Password Game - Main Window](https://user-images.githubusercontent.com/54780901/159022127-e36f9749-faf2-4e05-80a4-717059aa7f9b.png)
![Password Game - Helper Window](https://user-images.githubusercontent.com/54780901/159022080-3e63f6d7-888e-4b1f-8c7b-b2415f6e8763.png)

### Application Features

### Playing The Game

In this application (To play the game), users are prompted to enter a phrase that is a minimum of 15 characters in length. 

### Earning Points
Points are awarded to the user in the event that they enter a phrase that passes the minimum length requirement and they can receive an additional point if they choose the correct 

### Encryption Process
Located within the PasswordCreator class, is the createPassword function that acts as our phrase encryption algorithm. Using a character array, the function reverses the order of the user-entered phrase, inserts a randomly selected character on both ends, and inserts a random number in each blank space of the phrase. From the test examples shown below, when the user entered, “This Is A Test Password,” the generated password saved to the Passwords table in our database was, “!drowssaP2tseT2A2sI4sihT?”. Using an online password strength tool, this encrypted password would take a computer 28 nonillion years to figure out.

### Getting Help


### Database Implementation



![image](https://user-images.githubusercontent.com/54780901/159018701-ae607028-c489-4a75-91b6-9013e3b99158.png)




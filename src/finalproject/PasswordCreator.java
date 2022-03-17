/*
Programmer: Brian Dean
Purpose: Final Project Phase 4
IDE Used: NetBeans IDE
Date: 2/26/22
 */
package finalproject;
import java.util.Random;

//creating the PasswordCreator class that implements the password interface
public class PasswordCreator implements PasswordInterface
{
    //private String field for the password
    private String phrase;
    private final int MIN_SIZE = 15;
    
    //default constructor
    public PasswordCreator()
    {
        phrase = " ";
    }

    //method to the set the password field
    @Override
    public void setPhrase(String p)
    {
        this.phrase = p;
    }
    
    //method that creates and returns a unique password based on the user input
    @Override
    public String createPassword()
    {
        //turning the user's phrase into a char array
        char[] tempArray = phrase.toCharArray();
        
        //increasing the array that will hold the final password by two, to account for the added characters
        int size = tempArray.length;
        char[] passArray = new char[size + 2];

        //creating a random number object for the password encryption
        Random randomNumber = new Random();
        int number;

        //using a for loop to walk through the array and encrypt the password 
        for(int i = 0; i < size+2; i++)
        {
            //using an if statement that will enter a special character at the end of our password
            if(i == 0)
            {
                //finding a random number between 0-3
                number = randomNumber.nextInt(3);
                
                //inserting a special character at the end of the password based on the random number
                if(number == 0)
                {
                    passArray[(size + 1)] = '!';   
                }
                else if(number == 1)
                {
                    passArray[(size + 1)] = '$';  
                }
                else if(number == 2)
                {
                    passArray[(size + 1)] = '?';  
                }
                else if(number == 3)
                {
                    passArray[(size + 1)] = '%';  
                }
            }
            
            //using an if statement that will stop the array element from going out of bounds
            if(i < size)
            {
                //if statement that checks for a white space in the temporary array
                if(Character.isWhitespace(tempArray[i]))
                {
                    //finding a random number between 0-5
                    number = randomNumber.nextInt(5);
                    
                    //using a series of if else if statements to insert the random number into the white space found
                    if(number == 0)
                    {
                        passArray[(size - (i))] = '0';
                    }
                    else if(number == 1)
                    {
                        passArray[(size - (i))] = '1';
                    }
                    else if(number == 2)
                    {
                        passArray[(size - (i))] = '2';
                    }
                    else if(number == 3)
                    {
                        passArray[(size - (i))] = '3';
                    }
                    else if(number == 4)
                    {
                        passArray[(size - (i))] = '4';
                    }
                    else if(number == 5)
                    {
                        passArray[(size - (i))] = '5';
                    }
                }
            
                //an else statement that reverses the normal characters of our password
                else
                {
                    passArray[(size - i)] = tempArray[i]; 
                }
            }
            
            //if statement that runs when we reach the same size as our new passArray
            if(i == size)
            {
                //finding a random number between 0-3
                number = randomNumber.nextInt(3);
                
                //inserting a special character at the beginning of the password based on the random number
                if(number == 0)
                {
                    passArray[0] = '!';   
                }
                else if(number == 1)
                {
                    passArray[0] = '$';  
                }
                else if(number == 2)
                {
                    passArray[0] = '?';  
                }
                else if(number == 3)
                {
                    passArray[0] = '%';  
                }
            }
        }
        
        //changing the encryptin tempArray back into a String as the final password for the user
        String password = String.valueOf(passArray);
        
        //returning the password
        return password;
    }
    
    //method that checks that the user input is of the correct length
    @Override
    public Boolean checkLength(String p)
    {
        Boolean output = true;
        
        //creating the if else statement that will check the password length and return a correct response
        if(p.length() < MIN_SIZE)
        {
            output = false;
        }
        
        else if(p.length() >= MIN_SIZE)
        {
            output = true;
        }
        
        return output;
    }
    
}

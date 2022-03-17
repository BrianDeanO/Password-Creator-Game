/*
Programmer: Brian Dean
Purpose: Final Project Phase 4
IDE Used: NetBeans IDE
Date: 2/26/22
 */
package finalproject;

//creating the password game class that will use object composition with the password creator class
public class PasswordGame
{
    //creating the private fields
    private int userScore;
    private int hackerScore;
    private PasswordCreator obj;
    //update these with each button press and then set them both to zero when the rest method is used
    
    //default constructor
    public PasswordGame()
    {
        userScore = 0;
        hackerScore = 0;
        obj = new PasswordCreator();
    }
    
    //method that returns the correct response based on the length of the user entered phrase
    public String getLengthResponse(String phrase)
    {
        String output = " ";
        
        //if else statement that uses the boolean result from the object's checkLength method to display the correct response
        if(obj.checkLength(phrase))
        {
            output = "Good!";
            
            //setting the phrase of the PasswordCreator object since the length is good
            obj.setPhrase(phrase);
            
            //adding a point to the user since they entered a good sized phrase
            updateUserScore();
        }
        
        else
        {
            output = "Too Short!";
            
            //adding a point to the hacker, since the phrase was too short
            updateHackerScore();
        }
        
        return output;
    }
    
    //method that returns the password using object composition
    public String getPassword()
    {
        return obj.createPassword();
    }
    
    //method that increases the user's score by 1
    public void updateUserScore()
    {
        userScore += 1;
    }
    
    //method that increases the hacker's score by 1
    public void updateHackerScore()
    {
        hackerScore += 1;
    }

    //method that returns the current user score
    public int getUserScore()
    {
        return this.userScore;
    }
    
    //method that returns the current hacker score
    public int getHackerScore()
    {
        return this.hackerScore;
    }
    
    //method that sets both scores back to zero
    public void reset()
    {
        userScore = 0;
        hackerScore = 0;
    }
}

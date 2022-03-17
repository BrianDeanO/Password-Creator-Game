/*
Programmer: Brian Dean
Purpose: Final Project Phase 4
IDE Used: NetBeans IDE
Date: 2/26/22
 */
package finalproject;

//creating the interface that holds our abstract methods used for the PasswordCreator class
public interface PasswordInterface 
{
    //writing the abstract method headers
    void setPhrase(String phrase);
    String createPassword();
    Boolean checkLength(String phrase);    
}

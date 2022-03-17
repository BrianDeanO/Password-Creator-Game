/*
Programmer: Brian Dean
Purpose: Final Project Phase 4
IDE Used: NetBeans IDE
Date: 2/26/22
 */
package finalproject;
import java.sql.*;

//class that handles the creation of our FinalProjectDB Database 
public class FinalProjectDatabaseCreator {

    public static void main(String[] args) {
        
        //creating the database URL for the creation of our database
        final String URL = "jdbc:derby:FinalProjectDB;create=true";
        
        //enclosing the database creation in a try statement to catch the SQLException error if thrown
        try
        {
            //creating the connection object with the URL string 
            Connection conn = DriverManager.getConnection(URL); 
            
            //creating the statement object
            Statement stmt = conn.createStatement();
            
            //creating the String for our Phrases table to hold our pre-defined phrases, numbers, and characters
            String phraseTable = ("CREATE TABLE Phrases"
                              + "( PhraseID INTEGER NOT NULL PRIMARY KEY,"
                              + "  SavedPhrase VARCHAR(30),"
                              + "  SavedDigit INTEGER,"
                              + "  SaveChar CHAR(1) )");
            
            //creating the phrase table and database with the stmt object
            stmt.execute(phraseTable);
            
            //printing output that the table and database have been created
            System.out.print("FinalProjectDB Created. Phrases Table Created.\n");
            
            //creating a string that will return the result set of the Phrases table
            String phraseQry = ("SELECT * FROM Phrases");
            
            //creating a resultSet object with the sql statement
            ResultSet phraseResult = stmt.executeQuery(phraseQry);
            
            //getting the phrase table's metadata
            ResultSetMetaData phraseMetaData = phraseResult.getMetaData();
            
            //displaying the column count
            System.out.println("Phrases Table Column Count: " + phraseMetaData.getColumnCount());
            
            //using a for loop  to display the column names and type to confirm they have been properly made
            for(int i = 1; i <= phraseMetaData.getColumnCount(); i ++)
            {
                System.out.println("Column " + (i) + " - Name: " + phraseMetaData.getColumnName(i) 
                + "  Data Type: " + phraseMetaData.getColumnTypeName(i));
            }
            
            //creating the String for our Passwords table to hold the user's created passwords
            String passwordsTable = ("CREATE TABLE Passwords"
                                 + "( PasswordID INTEGER NOT NULL PRIMARY KEY,"
                                 + "  Password VARCHAR(35) )");
            
            //creating the passwords table
            stmt.execute(passwordsTable);
            
            //printing output that the table and database have been created
            System.out.print("\nPasswords Table Created.\n");
            
            //now creating a query to return all of the the password table
            String passwordQry = ("SELECT * FROM Passwords");
            
            //creaing the resultSet object
            ResultSet passwordResult = stmt.executeQuery(passwordQry);
            
            //getting the password table's metadata object
            ResultSetMetaData passwordMetaData = passwordResult.getMetaData();
            
            //displaying the column count
            System.out.println("Passwords Table Column Count: " + passwordMetaData.getColumnCount());
            
            //displaying the column names and data types found in the table
            for(int i = 1; i <= passwordMetaData.getColumnCount(); i ++)
            {
                System.out.println("Column " + (i) + " - Name: " + passwordMetaData.getColumnName(i) 
                + "  Data Type: " + passwordMetaData.getColumnTypeName(i));
            }
            
            //closing the connection and statement objects
            stmt.close();
            conn.close();
        }
        
        //catch clause that will handle the SQLException thrown
        catch(SQLException exception)
        {
            System.out.println(exception.getMessage());
        }

    }
    
}

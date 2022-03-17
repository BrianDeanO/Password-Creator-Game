/*
Programmer: Brian Dean
Purpose: Final Project Phase 4
IDE Used: NetBeans IDE
Date: 2/27/22
 */
import java.sql.*;

public class FinalProjectTest {

    public static void main(String[] args) throws SQLException
    {     
        //creating the database URL
        final String URL = "jdbc:derby:FinalProjectDB";
        
        //creating the connection object
        Connection conn = DriverManager.getConnection(URL);
            
        //creating the statement object that allows for scrolling and reading
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        
        //stmt.execute("DELETE FROM Passwords WHERE PasswordID IN (1,2,3,4,5)");
        stmt.execute("UPDATE Passwords SET PasswordID = 2 WHERE PasswordID = 7");
        
        //creating a result set object with our sql query
        //ResultSet resultSet = stmt.executeQuery("SELECT PasswordID, Password FROM Passwords");
        ResultSet resultSet = stmt.executeQuery("SELECT PhraseID, SavedPhrase, SavedDigit, SaveChar FROM Phrases");
        
        //sending the cursor to the last row
        resultSet.last();
        
        //assinging the row integer with the amount of rows in our table
        int rows = resultSet.getRow();
        
        //sending the cursor back to the first row
        resultSet.first();
           
        //label for the phrase table results
        System.out.print("Printing The Contents From the Phrases Table...\n");
        System.out.print("\nPre-Defined Phrases:\n");
        
        
        //printing just the pre-defined phrases that are used in the Password Helper application
        for(int i = 0; i < 10; i++)
        {
            //using the resultSet object to print the phrase ID, phrase, digit, and character
            System.out.print(resultSet.getInt(1));
            System.out.print("    ");
            System.out.print(resultSet.getString(2));
            System.out.print(" ");
            System.out.print(resultSet.getString(3));
            System.out.print(" ");
            System.out.print(resultSet.getString(4));
            System.out.print("\n");
            
            //sending the cursor down one row
            resultSet.next();
        }
        
        System.out.print("\nUser-Entered Phrases:\n");
        
        //now, we are printing the new phrases entered into the database by the user 
        for(int i = 10; i < rows; i++)
        {
            ////using the resultSet object to print the phrase ID, phrase, digit, and character
            // with the phrases being user-entered, they all have the same digit and character
            // therefore, those two attributes are all the same with values of 11 and }
            System.out.print(resultSet.getInt(1));
            System.out.print("    ");
            System.out.print(resultSet.getString(2));
            System.out.print(" ");
            System.out.print(resultSet.getString(3));
            System.out.print(" ");
            System.out.print(resultSet.getString(4));
            System.out.print("\n");
            
            //sending the cursor down one row
            resultSet.next();
        }
        
        //now creating the result set that returns the passwords saved into the database
        ResultSet passwordResultSet = stmt.executeQuery("SELECT PasswordID, Password FROM Passwords");
        
        //sending the cursor to the last row
        passwordResultSet.last();
        
        //assinging the row integer with the amount of rows in our table
        int passRows = passwordResultSet.getRow();
        
        //sending the cursor back to the first row
        passwordResultSet.first();
        
        System.out.print("\nPrinting The Contents From The Passwords Table...\n");
        
        //using a for loop to retrive the phrase and insert into the array
        for(int i = 0; i < passRows; i++)
        {
            //putting the phrase into the array with the getString method
            System.out.print(passwordResultSet.getInt(1));
            System.out.print("    ");
            System.out.print(passwordResultSet.getString(2));
            System.out.print("\n");
            
            //sending the cursor down one row
            passwordResultSet.next();
        }

        //closing the connection and stmt
        stmt.close();
        conn.close();     
    }
    
}

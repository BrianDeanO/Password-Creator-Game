/*
Programmer: Brian Dean
Purpose: Final Project Phase 4
IDE Used: NetBeans IDE
Date: 2/26/22
 */
package finalproject;
import java.sql.*;

//creating the class that will fill our Phrase table
public class FinalProjectDatabaseFiller {

    public static void main(String[] args) {
        
        //creating the database URL
        final String URL = "jdbc:derby:FinalProjectDB";
        
        try
        {
            //creating the connection object
            Connection conn = DriverManager.getConnection(URL);
            
            //creating the statement object
            Statement stmt = conn.createStatement();
            
            //adding ten rows into the phrase table
            // the ID number starts at 1 and ascends with each new row
            // and the digit starts at 0 and ascends with each new row
            
            //first row
            stmt.executeUpdate("INSERT INTO Phrases VALUES"
                    + "(1, 'This Is My Password', 0, '~')");
            
            //second row
            stmt.executeUpdate("INSERT INTO Phrases VALUES"
                    + "(2, 'Parks and Rec Is The Best', 1, '!')");
            
            //third row
            stmt.executeUpdate("INSERT INTO Phrases VALUES"
                    + "(3, 'Seinfeld Is The Greatest', 2, '#')");
            
            //fourth row
            stmt.executeUpdate("INSERT INTO Phrases VALUES"
                    + "(4, 'Star Wars Is Awesome', 3, '$')");
            
            //fifth row
            stmt.executeUpdate("INSERT INTO Phrases VALUES"
                    + "(5, 'Calzones Above Pizza', 4, '%')");
            
            //sixth row
            stmt.executeUpdate("INSERT INTO Phrases VALUES"
                    + "(6, 'Pancakes Beat Waffles', 5, '^')");
            
            //seventh row
            stmt.executeUpdate("INSERT INTO Phrases VALUES"
                    + "(7, 'Winter Is Coming', 6, '&')");
            
            //eigth row
            stmt.executeUpdate("INSERT INTO Phrases VALUES"
                    + "(8, 'To Infinity And Beyond', 7, '*')");
            
            //ninth row
            stmt.executeUpdate("INSERT INTO Phrases VALUES"
                    + "(9, 'Cornbread and Chili', 8, '+')");
            
            //tenth row
            stmt.executeUpdate("INSERT INTO Phrases VALUES"
                    + "(10, 'May The Force Be With You', 9, '?')");
            
            //creating a resultSet object and selecting all the records from Phrases
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Phrases");
            
            //creating a metadata object to help with displaying output
            ResultSetMetaData meta = resultSet.getMetaData();
            
            //using a while loop to move the cursor down the table rows
            while(resultSet.next())
            {
                //using a for loop to display the contents of the table
                for(int i = 1; i <= meta.getColumnCount(); i ++)
                {
                    System.out.print(resultSet.getString(i) + " ");
                } 
                
                System.out.print("\n");
            }
             
            //closing the statement and connection
            stmt.close();
            conn.close();
        }
        
        //catch clause for the SQLException
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
}

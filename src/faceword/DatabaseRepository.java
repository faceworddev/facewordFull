package faceword;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class DatabaseRepository 
{
    public static void InsertCredential(Connection con, int userId, String username, String password, String url)
    {
        Statement stmt;
        try 
        {
            stmt = con.createStatement();
            
            stmt.executeUpdate("INSERT INTO Credentials (Username, Password, Url, User_Id) VALUES ('" + username + "', '" + password + "', '" + url + "', '" + userId + "')");
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DatabaseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void UpdateCredential(Connection con, int userId, String username, String password, String url)
    {
        Statement stmt;
        
        try
        {
            stmt = con.createStatement();
            
            stmt.executeUpdate("UPDATE Credentials SET Username='" + username + "', Password='" + password + "' WHERE Url='" + url + "' AND User_Id=" + userId);
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DatabaseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList<String> GetListOfUserAccounts(Connection con, int userId)
    {
        Statement stmt;
        ArrayList<String> accounts = new ArrayList<>();
        
        try
        {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Url FROM Credentials WHERE User_Id=" + userId);
            
            while(rs.next())
            {
                accounts.add(rs.getString("Url"));
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DatabaseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return accounts;
    }
    
    public static ArrayList<Credential> GetListOfCredentials(Connection con, int userId)
    {
        Statement stmt;
        ArrayList<Credential> credentials = new ArrayList<>();
        
        try
        {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Credentials WHERE User_Id=" + userId);
            
            while(rs.next())
            {
                credentials.add(new Credential(rs.getString("Username"), rs.getString("Password"), rs.getString("Url"), rs.getInt("User_Id"), rs.getInt("Credential_Id")));
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DatabaseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return credentials;
    }
    
    public static void CreateUser(Connection con, byte[] image, String firstName, String lastName)
    {
        PreparedStatement stmt;
        
        try
        {
            InputStream input = new ByteArrayInputStream(image); 
            String sql = "INSERT INTO Users(LastName, FirstName, Image) VALUES(?,?,?)";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, lastName);
            stmt.setString(2, firstName);
            stmt.setBinaryStream(3, input, (int) image.length);
            stmt.execute();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DatabaseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList<Credential> GetUserCredentials(Connection con, int userId)
    {
        Statement stmt;
        ArrayList<Credential> credentials = new ArrayList<>();
        
        try
        {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Credentials WHERE User_Id=" + userId);
            
            while(rs.next())
            {
                credentials.add(new Credential(rs.getString("Username"), rs.getString("Password"), rs.getString("Url"), rs.getInt("User_Id"), rs.getInt("Credential_Id")));
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DatabaseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return credentials;
    }
    
    public static int AuthenticateUser(Connection con, byte[] image)
    {
        // TODO:
        // Send image to database in order to find user.
        // If a user is found then the user ID will be returned otherwise -1 one will returned.
        // For now let's just return user 1.
        return 3;
    }
    
    public static User GetUser(Connection con, int userId)
    {
        Statement stmt;
        User user = new User();
        
        try
        {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users WHERE User_Id=" + userId);
            
            while(rs.next())
            {
                user.setUserId(userId);
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setImage(rs.getBlob("Image"));
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DatabaseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }
    
    public static ArrayList<User> GetAllUsers(Connection con)
    {
        ArrayList<User> users = new ArrayList<User>();
        Statement stmt;

        try
        {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
            
            while(rs.next())
            {
                User user = new User();
                user.setUserId(rs.getInt("User_Id"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setImage(rs.getBlob("Image"));
                users.add(user);
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DatabaseRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }
}

package faceword;

import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class CreateUser
{
    public static int CreateUser(Webcam wc)
    {
        BufferedImage bi = wc.getImage();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try 
        {
            ImageIO.write(bi, "jpg", baos );
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] imageInByte = baos.toByteArray();
        
        String firstName = null;
        String lastName = null;
        
        while(firstName == null || firstName.equals(""))
        {
            firstName = (String)JOptionPane.showInputDialog(
                        null,
                        "What is your first name?",
                        "First Name?",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null);
        }
        
        while(lastName == null || lastName.equals(""))
        {
            lastName = (String)JOptionPane.showInputDialog(
                        null,
                        "What is your last name?",
                        "Last Name?",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null);
        }
        
        DatabaseConnectionManager dcm = new DatabaseConnectionManager();
        Connection con = dcm.GetNewConnection();
        DatabaseRepository.CreateUser(con, imageInByte, firstName, lastName);
        int userId = DatabaseRepository.GetUserCount(con);
        ServerUploader.SaveNewUserImageToServer(bi, userId);
        dcm.CloseConnection(con);
        return userId;
    }  
}


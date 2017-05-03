package faceword;

import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class LoginUser 
{
    public static int LoginUser(Webcam wc)
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
        
        DatabaseConnectionManager dcm = new DatabaseConnectionManager();
        Connection con = dcm.GetNewConnection();
       
        int userId = DatabaseRepository.AuthenticateUser(con, imageInByte);
        
        dcm.CloseConnection(con); 
        return userId;
    }
}

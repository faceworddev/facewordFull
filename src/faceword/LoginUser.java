package faceword;

import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class LoginUser 
{
    public static String LoginUser(Webcam wc)
    {
        BufferedImage bi = wc.getImage();
        String imageHashHex = LoginUser.GetHashHex(bi);
        ServerUploader.SaveCurrentUserToServer(bi, imageHashHex);
        String currentUserFaceId = FaceApiRepository.GetCurrentUserFaceId(imageHashHex);
        return currentUserFaceId;
    }
    
    private static String GetHashHex(BufferedImage bi)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi, "png", outputStream);
        } catch (IOException ex) {
            Logger.getLogger(LoginUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] data = outputStream.toByteArray();
        
        MessageDigest md;
        String hexString = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(data);
            byte[] hash = md.digest();
            for (int i=0; i < hash.length; i++) 
        { 
            hexString +=
            Integer.toString( ( hash[i] & 0xff ) + 0x100, 16).substring( 1 );
        } 
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hexString;
  } 

}

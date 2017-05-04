package faceword;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class ServerUploader 
{
    public static void SaveNewUserImageToServer(BufferedImage bi, int userId)
    {
        FTPClient client = new FTPClient();
        try 
        {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            client.connect("50.87.145.151");
            client.login("faceworddev", "diCoOkHxmKpGgby!B0");
            client.setFileType(FTP.BINARY_FILE_TYPE);
            client.storeFile("public_html/UserImages/"+userId+".png", is);
            client.logout();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    public static void SaveCurrentUserToServer(BufferedImage bi, String imageHashHex)
    {
        FTPClient client = new FTPClient();
        try 
        {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bi, "png", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            client.connect("50.87.145.151");
            client.login("faceworddev", "diCoOkHxmKpGgby!B0");
            client.setFileType(FTP.BINARY_FILE_TYPE);
            client.storeFile("public_html/CurrentUser/"+imageHashHex+".png", is);
            client.logout();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
}

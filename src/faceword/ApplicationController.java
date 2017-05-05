package faceword;

import java.sql.Connection;
import java.util.ArrayList;
import org.openqa.selenium.chrome.ChromeDriver;

public class ApplicationController
{
    private static MainWindow mw;
    private static LoginServiceGui lsg;
    private static AddAccountWindow aaw;
    private static ArrayList<Credential> credentials;
    private int userId = 0;
    private int faceListId = 0;
    private int userCount = 0;
    private ArrayList<PersistedFaceId> userLookup;
    final static String macDriverPath = "/Users/TacoFucker/NetBeansProjects/facewordFull/ExternalLibraries/chromedriver";
    final static String windowsDriverPath = "C:\\Users\\tcordonnier2\\Documents\\NetBeansProjects\\FaceWord\\ExternalLibraries\\chromedriver.exe";
    private ChromeDriver driver;
    
    public ApplicationController ()
    {
        System.setProperty("webdriver.chrome.driver", GetDriverPath());
        faceListId = FaceApiRepository.CreateNewFaceList();
        DatabaseConnectionManager dcm = new DatabaseConnectionManager();
        Connection con = dcm.GetNewConnection();
        userCount = DatabaseRepository.GetUserCount(con);
        dcm.CloseConnection(con);
        FaceApiRepository.AddUserImagesToFaceList(faceListId, userCount);
        userLookup = FaceApiRepository.PopulatePersistedFaceIdArrayList(faceListId);
        this.DisplayLoginScreen();
    }

    public void setDriver(ChromeDriver driver) 
    {
        this.driver = driver;
    }
    
    public ChromeDriver getDriver() 
    {
        return driver;
    }

    public int GetUserIdFromUserLookup(String faceId)
    {
        for(int i = 0; i< userLookup.size(); i++)
        {
            if(userLookup.get(i).getFaceId().equals(faceId))
            {
                return userLookup.get(i).getUserId();
            }
        }
        
        return 0;
    }
    
    public static String GetDriverPath()
    {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win"))
        {
            return windowsDriverPath;
        }
        else if(os.contains("mac"))
        {
            return macDriverPath;
        }   
        else
        {
            return null;
        }
    }
    
    public void UpdateUsersCountAndUserLookup()
    {
        userCount++;
        userLookup = FaceApiRepository.PopulatePersistedFaceIdArrayList(faceListId);
    }
    
    public int getFaceListId() 
    {
        return faceListId;
    }

    public void DisplayLoginScreen()
    {
        this.clean();
        mw = new MainWindow(this);
        mw.setVisible(true);
        mw.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);  
    }
    
    public void DisplayLoggedInGui(int id)
    {
        this.clean();
        this.userId = id;
        
        DatabaseConnectionManager dcm = new DatabaseConnectionManager();
        Connection con = dcm.GetNewConnection();
        credentials = DatabaseRepository.GetListOfCredentials(con, this.userId);
        lsg = new LoginServiceGui(credentials, this, this.userId);
        lsg.EmptyComboBox();
        lsg.PopulateComoboBox();
        lsg.show(true);
        dcm.CloseConnection(con);
    }
    
    public void DisplayAddAccount(int ui)
    {
        this.clean();
        this.userId = ui;
        aaw = new AddAccountWindow(this, this.userId);
        aaw.show(true);
    }
    
    public void clean()
    {
        if(lsg != null)
        {
            lsg.EmptyComboBox();
            lsg.show(false);
            lsg.dispose();
        }
        
        if(aaw != null)
        {
            aaw.show(false);
            aaw.dispose();
        }
        
        if(mw != null)
        {
            mw.show(false);
            mw.dispose();
        }
        
        userId = 0;
        mw = null;
        credentials = null;
        lsg = null;
    }
}

    
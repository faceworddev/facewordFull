package faceword;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

public class ApplicationController
{
    private static MainWindow mw;
    private static LoginServiceGui lsg;
    private static AddAccountWindow aaw;
    private static ArrayList<Credential> credentials;
    private int userId = 0;

    public ApplicationController ()
    {
        this.DisplayLoginScreen();
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

    
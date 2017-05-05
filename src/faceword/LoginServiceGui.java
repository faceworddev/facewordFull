package faceword;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginServiceGui extends javax.swing.JFrame 
{
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    ArrayList<Credential> credentials;
    private ApplicationController controller;
    private int userId;
    private User user;
    
    public LoginServiceGui(ArrayList<Credential> credentials, ApplicationController ac, int ui) 
    {
        this.userId = ui;
        DatabaseConnectionManager dcm = new DatabaseConnectionManager();
        Connection con = dcm.GetNewConnection();
        user = DatabaseRepository.GetUser(con, userId);
        dcm.CloseConnection(con);
        this.controller = ac;
        this.credentials = credentials;
        this.setUndecorated(true);
        initComponents();
        this.UserNameLabel.setText("You are logged in as "+user.getFirstName()+ " " + user.getLastName());
        this.setLocation(dim.width/2-this.getSize().width/2, 25);
        this.PopulateComoboBox();
        this.goButton.addActionListener(new GoAction());
    }

    public void PopulateComoboBox()
    {
        for(int i = 0; i < credentials.size(); i++)
        {
            this.websiteComboBox.addItem(credentials.get(i).getUrl());
        }
        this.websiteComboBox.addItem("Add/Change Account");
        this.websiteComboBox.addItem("Sign Out");
        this.websiteComboBox.addItem("Exit Program");
    }
    
    public void EmptyComboBox()
    {
        this.websiteComboBox.removeAllItems();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        websiteComboBox = new javax.swing.JComboBox<>();
        goButton = new javax.swing.JButton();
        UserNameLabel = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);

        websiteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        goButton.setText("Go");

        UserNameLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(UserNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(websiteComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(goButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(websiteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(goButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(UserNameLabel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel UserNameLabel;
    private javax.swing.JButton goButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> websiteComboBox;
    // End of variables declaration//GEN-END:variables
    class GoAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            String item = websiteComboBox.getSelectedItem().toString();
            
            if(item.equals("Facebook"))
            {
                for(int i = 0; i< credentials.size(); i++)
                {
                    if(credentials.get(i).getUrl().equals("Facebook"))
                    {
                        controller.setDriver(new ChromeDriver());
                        LoginRepository.loginFacebook(credentials.get(i), controller.getDriver());
                        break;
                    }
                }
            }
            else if(item.equals("Instagram"))
            {
                for(int i = 0; i< credentials.size(); i++)
                {
                    if(credentials.get(i).getUrl().equals("Instagram"))
                    {
                        controller.setDriver(new ChromeDriver());
                        LoginRepository.loginInstagram(credentials.get(i), controller.getDriver());
                        break;
                    }
                }
            }
            else if(item.equals("Twitter"))
            {
                for(int i = 0; i< credentials.size(); i++)
                {
                    if(credentials.get(i).getUrl().equals("Twitter"))
                    {
                        controller.setDriver(new ChromeDriver());
                        LoginRepository.loginTwitter(credentials.get(i), controller.getDriver());
                        break;
                    }
                }
            }
            else if(item.equals("Pinterest"))
            {
                for(int i = 0; i< credentials.size(); i++)
                {
                    if(credentials.get(i).getUrl().equals("Pinterest"))
                    {
                        controller.setDriver(new ChromeDriver());
                        LoginRepository.loginPinterest(credentials.get(i), controller.getDriver());
                        break;
                    }
                }
            }
            else if(item.equals("Add/Change Account"))
            {
                controller.DisplayAddAccount(userId);
            }
            else if(item.equals("Sign Out"))
            {
                try
                {
                controller.getDriver().close();
                }
                catch (Exception ex)
                {
                    System.out.println(controller.getDriver().toString());
                }
                
                controller.DisplayLoginScreen();
            }
            else if(item.equals("Exit Program"))
            {
                try
                {
                controller.getDriver().close();
                }
                catch (Exception ex)
                {
                    System.out.println(controller.getDriver().toString());
                }
                
                FaceApiRepository.DeleteFaceList(controller.getFaceListId());
                System.exit(0);
            }
        } 
    }
}
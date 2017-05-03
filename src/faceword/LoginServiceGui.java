package faceword;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginServiceGui extends javax.swing.JFrame 
{
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    ArrayList<Credential> credentials;
    private ApplicationController controller;
    private int userId;
    
    
    public LoginServiceGui(ArrayList<Credential> credentials, ApplicationController ac, int ui) 
    {
        this.userId = ui;
        this.controller = ac;
        this.credentials = credentials;
        this.setUndecorated(true);
        initComponents();
        this.setLocation(dim.width/2-this.getSize().width/2, 0);
        this.PopulateComoboBox();
        this.goButton.addActionListener(new GoAction());
    }

    public void PopulateComoboBox()
    {
        for(int i = 0; i < credentials.size(); i++)
        {
            this.websiteComboBox.addItem(credentials.get(i).getUrl());
        }
        this.websiteComboBox.addItem("Add Account");
        this.websiteComboBox.addItem("Sign Out");
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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);

        websiteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        goButton.setText("Go");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(websiteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
                        LoginRepository.loginFacebook(credentials.get(i));
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
                        LoginRepository.loginInstagram(credentials.get(i));
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
                        LoginRepository.loginTwitter(credentials.get(i));
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
                        LoginRepository.loginPinterest(credentials.get(i));
                        break;
                    }
                }
            }
            else if(item.equals("Gmail"))
            {
                for(int i = 0; i< credentials.size(); i++)
                {
                    if(credentials.get(i).getUrl().equals("Gmail"))
                    {
                        LoginRepository.loginGmail(credentials.get(i));
                        break;
                    }
                }
            }
            else if(item.equals("Add Account"))
            {
                controller.DisplayAddAccount(userId);
            }
            else if(item.equals("Sign Out"))
            {
                controller.DisplayLoginScreen();
            }
        } 
    }
}
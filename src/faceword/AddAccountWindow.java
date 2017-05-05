package faceword;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class AddAccountWindow extends javax.swing.JFrame
{   
    private ApplicationController controller;
    private int userId = 0;
    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    
    public AddAccountWindow(ApplicationController ac, int ui)
    {
        this.controller = ac;
        this.userId = ui;
        this.setUndecorated(true);
        initComponents();
        this.setLocation(dim.width/2-this.getSize().width/2, 25);
        this.setVisible(true);
        this.setResizable(false);
        this.submitButton.addActionListener(new AddAccount());
        this.PopulateComboBoxOptions();
        this.urlField.setVisible(false);
        this.urlLabel.setVisible(false);
    }
    
    public void PopulateComboBoxOptions()
    {
        try 
        {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document dDoc = builder.parse("src/XML/Websites.xml");
            NodeList names = dDoc.getElementsByTagName("Name");
            for(int i = 0; i < names.getLength(); i++)
            {
                Element e = (Element)names.item(i);
                this.accountComboBox.addItem(((Element)names.item(i)).getFirstChild().getTextContent().trim());
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(AddAccountWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
                
    public String GetUrl()
    {
        if(this.accountComboBox.getSelectedIndex() == this.accountComboBox.getItemCount()-1)
        {
            return this.urlField.getText();
        }
        else
        {
            return this.accountComboBox.getSelectedItem().toString();
        }
    }
         
    public String GetUsername()
    {
        return this.usernameField.getText();
    }
    
    public String GetPassword()
    {
        return String.valueOf(this.passwordField.getPassword());
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        accountComboBox = new javax.swing.JComboBox<>();
        accountLabel = new javax.swing.JLabel();
        urlLabel = new javax.swing.JLabel();
        urlField = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        submitButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        fileExitMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        helpContentsMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        accountComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                accountComboBoxItemStateChanged(evt);
            }
        });

        accountLabel.setText("Select Accont");

        urlLabel.setText("Enter URL");

        usernameLabel.setText("Username");

        passwordLabel.setText("Password");

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        fileExitMenuItem.setText("Exit");
        jMenu1.add(fileExitMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        helpContentsMenuItem.setText("Help Contents");
        jMenu2.add(helpContentsMenuItem);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(accountLabel)
                    .addComponent(urlLabel)
                    .addComponent(usernameLabel)
                    .addComponent(passwordLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordField)
                    .addComponent(usernameField)
                    .addComponent(urlField)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(accountComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(submitButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accountComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(urlLabel)
                    .addComponent(urlField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(submitButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void accountComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_accountComboBoxItemStateChanged

       if(this.accountComboBox.getSelectedIndex() == this.accountComboBox.getItemCount()-1)
       {
           this.urlField.setVisible(true);
           this.urlLabel.setVisible(true);
       }
       else
       {
           this.urlField.setVisible(false);
           this.urlLabel.setVisible(false);
       }
    }//GEN-LAST:event_accountComboBoxItemStateChanged

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed

    }//GEN-LAST:event_submitButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> accountComboBox;
    private javax.swing.JLabel accountLabel;
    private javax.swing.JMenuItem fileExitMenuItem;
    private javax.swing.JMenuItem helpContentsMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton submitButton;
    private javax.swing.JTextField urlField;
    private javax.swing.JLabel urlLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables

    
    class AddAccount implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            final DatabaseConnectionManager dcm = new DatabaseConnectionManager();

            String username = GetUsername();
            String password = GetPassword();
            String url = GetUrl();
            boolean wasUpdated = false;

            Connection con = dcm.GetNewConnection();

            // A user cannot have more than one account per url/app so if they try adding another Facebook account it will overwrite the one they currently have on file.

            ArrayList<String> accounts = DatabaseRepository.GetListOfUserAccounts(con, userId);

            for(int i = 0; i < accounts.size(); i++)
            {
                if(accounts.get(i).equals(url))
                {
                    wasUpdated = true;
                    DatabaseRepository.UpdateCredential(con, userId, username, password, url);
                }
            }

            if(!wasUpdated)
            {
                DatabaseRepository.InsertCredential(con, userId, username, password, url);
            }

            dcm.CloseConnection(con);
            controller.DisplayLoggedInGui(userId);
        }
        
    }
}




package faceword;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.HEIGHT;
import javax.swing.JOptionPane;

public class MainWindow extends javax.swing.JFrame 
{
    
    private Toolkit tk = Toolkit.getDefaultToolkit();
    private Dimension halfSize = new Dimension(tk.getScreenSize().width/2, tk.getScreenSize().height/2);
    private Webcam wc = Webcam.getDefault();
    private ApplicationController controller;
    
    public MainWindow(ApplicationController ac) 
    {
        if(wc != null)
        {
            wc.close();
        }
        
        wc.setViewSize(WebcamResolution.VGA.getSize());
        this.controller = ac;
        this.setUndecorated(true);
        initComponents();
        this.CreateAccountButton.addActionListener(new CreateAccountAction());
        this.LoginButton.addActionListener(new LoginAction());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CamPanel = new WebcamPanel(wc);
        LoginButton = new javax.swing.JButton();
        CreateAccountButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 0, 204));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        CamPanel.setMaximumSize(halfSize);
        CamPanel.setMinimumSize(halfSize);
        CamPanel.setPreferredSize(halfSize);

        LoginButton.setBackground(new java.awt.Color(149, 165, 166));
        LoginButton.setForeground(new java.awt.Color(0, 0, 0));
        LoginButton.setText("Login");
        CamPanel.add(LoginButton);

        CreateAccountButton.setBackground(new java.awt.Color(149, 165, 166));
        CreateAccountButton.setForeground(new java.awt.Color(0, 0, 0));
        CreateAccountButton.setText("Create Account");
        CamPanel.add(CreateAccountButton);

        getContentPane().add(CamPanel, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CamPanel;
    private javax.swing.JButton CreateAccountButton;
    private javax.swing.JButton LoginButton;
    // End of variables declaration//GEN-END:variables

    class CreateAccountAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            int userId = CreateUser.CreateUser(wc);
            FaceApiRepository.AddSingleUserImage(controller.getFaceListId(), userId);
            controller.UpdateUsersCountAndUserLookup();
            controller.DisplayLoggedInGui(userId);
            wc.close();
        }
    }

    class LoginAction implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String currentUserFaceId = LoginUser.LoginUser(wc);
            String match = FaceApiRepository.AuthenticateFace(controller.getFaceListId(), currentUserFaceId);
            if(match != null)
            {
                int userId = controller.GetUserIdFromUserLookup(match);
                if(userId > 0)
                {
                    controller.DisplayLoggedInGui(userId);
                    wc.close();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "No user found.", "Not Found!!1", HEIGHT);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No user found.", "Not Found!!1", HEIGHT);
            }
        }
    }
}

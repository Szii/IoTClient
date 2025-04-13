/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginScreen;

import Base.BasePresenterInterface;
import Components.Button;
import Components.Label;
import Components.MessagePopup;
import Components.Panel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Implementation of login GUI.
 * @author brune
 */
public class LoginScreenGUI implements LoginScreenGUIInterface {
    LoginScreenPresenterInterface presenter; 
     Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
   MessagePopup popup;
    Font infoFont;
    Font textBoxFont;

   
    
    static JFrame loginwindow;
    static Container con;


    JLabel loginErrorLabel;  

    JTextField loginNameTextField;
    JPasswordField loginPasswdTextField;

    // private final ArrayList<IregisterFromGUI> listeners;
     /**
      * Creates new login screen component
      */
    public LoginScreenGUI(){
     infoFont = new Font ("Comic Sans", Font.BOLD, 17);
     textBoxFont = new Font ("Comic Sans", Font.BOLD, 19);
        
   //  this.listeners = new ArrayList<IregisterFromGUI>();
    }
    /*
     private void notifyListenersOnLoginClicked() {
        for (final IregisterFromGUI listener : listeners) {
            listener.checkLogin();
        }
    }
     */
     @Override
   public void setPresenter(BasePresenterInterface presenter){
       this.presenter = (LoginScreenPresenterInterface) presenter;
   }
    /* 
    private void notifyListenersOnRegisterClicked(ActionEvent e) {
        for (final IregisterFromGUI listener : listeners) {
            listener.checkRegister(e);
        }
    }

    // Subscribe a listener
    public void addListener(final IregisterFromGUI listener) {
        listeners.add(listener);
    }
    */
    
    
   /**
    * Metoda vytvoří login screen, který uživateli umožňuje přihlášení se k existujícímu ůčtu, nebo přejít
    * k vytvoření nového účtu.
    */
   
   
   
   private JFrame getFrame(){
     loginwindow = new JFrame(); // vytvoření objektu "window"
    loginwindow.setSize(450,250);
    loginwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    loginwindow.setUndecorated(true);
    loginwindow.setLocationRelativeTo(null);
    con = loginwindow.getContentPane(); 
    con.setLayout(new BoxLayout(con,BoxLayout.Y_AXIS));
    return loginwindow;
   
   }
   
   private Panel getLoginNamePanel(){
    Panel loginnamePanel = new Panel();
    loginnamePanel.setLayout(new GridLayout(1,2));
  //  loginnamePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
   
    
    Label loginNameLabel = new Label("Name",SwingConstants.CENTER);
    loginNameLabel.setFont(infoFont);
    loginnamePanel.add(loginNameLabel);
    
    loginNameTextField = new JTextField();
    loginNameTextField.setFont(textBoxFont);
    loginnamePanel.add(loginNameTextField);
    
    
    return loginnamePanel;
   }
   
   private Panel getLoginPasswordPanel(){
     Panel loginpasswdPanel = new Panel();
    loginpasswdPanel.setLayout(new GridLayout(1,2));
   // loginpasswdPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    Label loginPasswdLabel = new Label("Password",SwingConstants.CENTER);
    loginPasswdLabel.setFont(infoFont);
    loginpasswdPanel.add(loginPasswdLabel);
    
     loginPasswdTextField = new JPasswordField();
    loginPasswdTextField.setFont(textBoxFont);
    loginpasswdPanel.add(loginPasswdTextField);
   
      return loginpasswdPanel;
   }
   
   private Panel getErrorPanel(){
    Panel loginErrorPanel = new Panel();
    loginErrorPanel.setLayout(new GridLayout(1,1));
    loginErrorPanel.setVisible(true);
    
    loginErrorLabel = new JLabel("",SwingConstants.CENTER);
    loginErrorPanel.add(loginErrorLabel);
    return loginErrorPanel;
   }
   
   
   private Panel getNewAccountButtonPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        Button newAccountButton = new Button ("New account");
        newAccountButton.addActionListener((ActionEvent e)->{
            presenter.onRegisterConfirm();
        });
        panel.add(newAccountButton);
        return panel;
   }
   
    private Panel getLoginButtonPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        Button loginButton = new Button ("Login");
        loginButton.setFocusPainted(false);
        panel.add(loginButton);
        loginButton.addActionListener((ActionEvent e)->{
            presenter.onLoginConfirm();
        });
        return panel;
   }
    
    private Panel getExitButtonPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        Button loginExitButton = new Button ("End");
        loginExitButton.addActionListener((ActionEvent e)->{
            presenter.onLoginCancel();
        });
        panel.add(loginExitButton);
        return panel;
   }
    
    private Panel getButtonsPanelHolder(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(getButtonsPanel(),BorderLayout.SOUTH);
        return panel;
    }
   
   private Panel getButtonsPanel(){
    Panel loginPanel = new Panel();
   // loginPanel.setLayout(new GridLayout(1,3,40,0));
    loginPanel.setLayout(new BoxLayout(loginPanel,BoxLayout.X_AXIS));
    loginPanel.setVisible(true);
    loginPanel.setBorder(new EmptyBorder(10,10,10,10));
  
    loginPanel.add(getNewAccountButtonPanel());
    loginPanel.add(getLoginButtonPanel());
    loginPanel.add(getExitButtonPanel());


    return loginPanel;
   }


   @Override
   public void setErrorText(String text){
        loginErrorLabel.setText(text);
   } 

    @Override
    public void removeLoginScreen() {
       loginwindow.dispose();
    }
    @Override
    public String getLoginName() {
        return loginNameTextField.getText();
    }

    @Override
    public String getLoginPassword() {
        return loginPasswdTextField.getText();
    }

    @Override
    public void initView() {
       // getLoginScreen();
       
       loginwindow = getFrame();
       loginwindow.add(Box.createRigidArea(new Dimension(0, 5)));
       loginwindow.add(this.getLoginNamePanel());
       loginwindow.add(Box.createRigidArea(new Dimension(0, 5)));
       loginwindow.add(this.getLoginPasswordPanel());
       loginwindow.add(this.getErrorPanel());
       loginwindow.add(this.getButtonsPanelHolder());
       loginwindow.add(Box.createRigidArea(new Dimension(0, 5)));
       loginwindow.setVisible(true);
       
    }

    @Override
    public void updateGUI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

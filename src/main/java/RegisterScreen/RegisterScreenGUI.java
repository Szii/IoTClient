/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegisterScreen;

import Base.BasePresenterInterface;
import Components.Button;
import Components.Label;
import Components.Panel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Implementation of register screen GUI
 * @author brune
 */
public class RegisterScreenGUI implements RegisterScreenGUIInterface {
    RegisterScreenPresenterInterface presenter; 
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    Font infoFont;
    Font textBoxFont;
    
    static JFrame registerWindow;
    static Container con;
    
    JLabel registerNameLabel ;
    JLabel   registerPasswdLabel;   
    JTextField registerNameTextField; 
    JPasswordField registerPasswdTextField;
    JPasswordField registerCheckPasswdTextField;
    JLabel registerErrorLabel;
    
    // private final ArrayList<IregisterFromGUI> listeners;
     /**
      * Creates new register screen GUI component
      */
    public RegisterScreenGUI(){
     infoFont = new Font ("Comic Sans", Font.BOLD, 17);
     textBoxFont = new Font ("Comic Sans", Font.BOLD, 19);
        
   //  this.listeners = new ArrayList<IregisterFromGUI>();
    // thisRef = this;
    }

     @Override
   public void setPresenter(BasePresenterInterface presenter){
       this.presenter = (RegisterScreenPresenterInterface) presenter;
   }
    

 
   /**
    * Metoda reigsterScreen se stará o vykreslení okna, které umožňuje uživateli provést registraci nového účtu.
    */
   
   
   private JFrame getFrame(){
    registerWindow = new JFrame(); // vytvoření objektu "window"
    registerWindow.setSize(400,300);
    registerWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    registerWindow.setUndecorated(true);
    registerWindow.setLocationRelativeTo(null);  
    con = registerWindow.getContentPane(); 
    con.setLayout(new BoxLayout(con,BoxLayout.Y_AXIS));
    return registerWindow;
   }
   
   private Panel getRegisterNamePanel(){
    Panel  registerNamePanel = new Panel();
    registerNamePanel.setLayout(new GridLayout(1,2));
   // registerNamePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
   
    Label registerNameLabel = new Label("Name",SwingConstants.CENTER);
    registerNameLabel.setFont(infoFont);
    registerNamePanel.add(registerNameLabel);
    
    registerNameTextField = new JTextField();
    registerNameTextField.setFont(textBoxFont);
    registerNamePanel.add(registerNameTextField);
    return registerNamePanel;
   }
   
   private Panel getRegisterPasswdPanel(){
     Panel registerPasswdPanel = new Panel();
    registerPasswdPanel.setLayout(new GridLayout(1,2));

   // registerPasswdPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    Label registerPasswdLabel = new Label("Password",SwingConstants.CENTER);
    registerPasswdLabel.setFont(infoFont);
    registerPasswdPanel.add(registerPasswdLabel);
    
    registerPasswdTextField = new JPasswordField();
    registerPasswdTextField.setFont(textBoxFont);
    registerPasswdPanel.add(registerPasswdTextField);
    
    return registerPasswdPanel;
   }
   
   private Panel getRegisterPasswdConfirmPanel() {
    Panel registerCheckPasswdPanel = new Panel();
    registerCheckPasswdPanel.setLayout(new GridLayout(1,2));
   // registerCheckPasswdPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    Label registerCheckPasswdLabel = new Label("Password check",SwingConstants.CENTER);
    registerCheckPasswdLabel.setFont(infoFont);
    registerCheckPasswdPanel.add(registerCheckPasswdLabel);
    
    registerCheckPasswdTextField = new JPasswordField();
    registerCheckPasswdTextField.setFont(textBoxFont);
    registerCheckPasswdPanel.add(registerCheckPasswdTextField);
    
    return registerCheckPasswdPanel;
   }
   
   
   private Panel getErrorPanel(){
    Panel registerErrorPanel = new Panel();
    registerErrorPanel.setLayout(new GridLayout(1,1));
    registerWindow.add(registerErrorPanel);
    registerErrorPanel.setVisible(true);            
    
    registerErrorLabel = new JLabel("",SwingConstants.CENTER);
    registerErrorPanel.add(registerErrorLabel);
    
    return registerErrorPanel;
   }
   
   private Panel getButtonsPanel(){
    Panel buttonsPanel = new Panel();
    buttonsPanel.setBorder(new EmptyBorder(10,10,10,10));
    buttonsPanel.setLayout(new GridLayout(1,2,40,0));  
    buttonsPanel.add(getRegisterButtonPanel());
    buttonsPanel.add(getReturnButtonPanel()); 
    return buttonsPanel;
   }
   
   private Panel getButtonsPanelHolder(){
       Panel panel = new Panel();
       panel.setLayout(new BorderLayout());
       panel.add(getButtonsPanel(),BorderLayout.SOUTH);
       return panel;
   }
   
   private Panel getRegisterButtonPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
       
        Button registerButton = new Button ("Register");
        registerButton.addActionListener((ActionEvent e)->{
            presenter.onCreateAccount();
    
        });
        panel.add(registerButton);
        return panel;
   }
   
     private Panel getReturnButtonPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
       
        Button registerExitButton = new Button ("Back to login");
        registerExitButton.addActionListener((ActionEvent e)->{
            presenter.onRegisterCancel();
        });
        panel.add(registerExitButton);
        return panel;
   }
   
   @Override
   public void setErrorMessage(String text){
        registerErrorLabel.setText(text);
   } 


    @Override
    public String getRegisterName() {
        return registerNameTextField.getText();
    }

    @Override
    public String getRegisterPassword() {
       return registerPasswdTextField.getText();
    }

    @Override
    public String getRegisterConfirmPassword() {
        return registerCheckPasswdTextField.getText();
    }

    @Override
    public void removeRegisterScreen() {
         registerWindow.dispose();
    }

    @Override
    public void initView() {
         //getRegisterScreen();
         registerWindow = getFrame();
         registerWindow.add(this.getRegisterNamePanel());
         registerWindow.add(this.getRegisterPasswdPanel());
         registerWindow.add(this.getRegisterPasswdConfirmPanel());
         registerWindow.add(this.getErrorPanel());
         registerWindow.add(this.getButtonsPanelHolder());
         registerWindow.setVisible(true);
    }

    @Override
    public void updateGUI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}

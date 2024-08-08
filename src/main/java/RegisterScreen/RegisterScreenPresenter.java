/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegisterScreen;

import LoginScreen.*;
import Model.ServiceManager;
import com.irrigation.Messages.Code;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 * Implementation of register screen controls
 * @author brune
 */
public class RegisterScreenPresenter implements RegisterScreenPresenterInterface {
     RegisterScreenGUIInterface gui;   
     ServiceManager model;
     /**
      * Creates new controls for register screen component
      * @param gui Component to be controlled with
      * @param model service manager
      */
     public RegisterScreenPresenter(RegisterScreenGUIInterface gui,ServiceManager model){
         this.gui = gui;
         gui.setPresenter(this);
         this.model = model;
     }
    

    /**
     * Methods inits the view
     */
    public void getRegisterScreen(){
       
           gui.initView();
          
    }
       
    private void removeRegisterScreen(){
            LoginScreenGUIInterface view = new LoginScreenGUI();
            LoginScreenPresenterInterface presenter = new LoginScreenPresenter(view,model);
            presenter.initView();
            gui.removeRegisterScreen();
    }
    
    /**
     * Method shows the popup with message 
     * @param text content of message
     */
     public void showPopupMessage(String text){
        SwingWorker worker = new SwingWorker<Void, Void>() {
                @Override
                public Void doInBackground() {   
                    gui.setErrorMessage(text);
                    return null;
                }
         };
        worker.execute(); 
     
    }
    
    
    private Code getAnswerToLogin(String loginName,String password){
        try {
               return model.checkLogin(loginName, password);
            } catch (InterruptedException ex) {
                Logger.getLogger(RegisterScreenGUI.class.getName()).log(Level.SEVERE, null, ex);
                return Code.FAILURE;
            }
    }

   

    @Override
    public void onRegisterCancel() {
            removeRegisterScreen();
    }

    @Override
    public void onCreateAccount() {
        
         try {
             if (model.doesUserExist(gui.getRegisterName()).equals(Code.FAILURE)){
                 showPopupMessage("This name is already taken");
             }
             
             else if(gui.getRegisterPassword().length() < 4){
                 showPopupMessage("Password too short");
             }
             else if(gui.getRegisterName().length() < 4){
                 showPopupMessage("Name is too short");
             }
             else if(gui.getRegisterPassword().equals(gui.getRegisterConfirmPassword())){
                 try {
                     model.addUser(gui.getRegisterName(),gui.getRegisterPassword());
                     showPopupMessage("Registration complete");
                 } catch (InterruptedException ex) {
                     Logger.getLogger(RegisterScreenPresenter.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
             else{
                 
                 showPopupMessage("Passwords do not match");
             }
         } catch (InterruptedException ex) {
             Logger.getLogger(RegisterScreenPresenter.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void initView() {
        gui.initView();
    }
}

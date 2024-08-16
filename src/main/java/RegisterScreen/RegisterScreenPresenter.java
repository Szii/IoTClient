/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegisterScreen;

import LoginScreen.*;
import Model.ServiceManager;
import com.irrigation.Messages.MessageFormat.Code;
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
   

    @Override
    public void onRegisterCancel() {
            removeRegisterScreen();
    }

    @Override
    public void onCreateAccount() {
        if(gui.getRegisterPassword().length() < 4){
            showPopupMessage("Password too short");
        }
        else if(!gui.getRegisterPassword().equals(gui.getRegisterConfirmPassword())){
            showPopupMessage("Passwords do not match");
        }
        else if(gui.getRegisterName().length() < 4){
            showPopupMessage("Name is too short");
        }
        else if (model.doesUserExist(gui.getRegisterName()).equals(Code.SUCCESS)){
            showPopupMessage("This name is already taken");
        }
        else if(gui.getRegisterPassword().equals(gui.getRegisterConfirmPassword())){
                if(model.addUser(gui.getRegisterName(),gui.getRegisterPassword()).equals(Code.SUCCESS)){
                    showPopupMessage("Registration complete");
                }
                else{
                    showPopupMessage("Something went wrong");
                }
        }
    
    }

    @Override
    public void initView() {
        gui.initView();
    }
}

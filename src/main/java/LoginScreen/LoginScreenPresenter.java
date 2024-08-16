
  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginScreen;

import Components.Panel;
import Constants.ConstantsList;
import MainFrame.MainFrameGUI;
import MainFrame.MainFrameGUIInterface;
import MainFrame.MainFramePresenter;
import MainFrame.MainFramePresenterInterface;
import Model.ServiceManager;
import RegisterScreen.RegisterScreenGUI;
import RegisterScreen.RegisterScreenGUIInterface;
import RegisterScreen.RegisterScreenPresenter;
import RegisterScreen.RegisterScreenPresenterInterface;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import MainPanel.*;
import Model.SensorsPanelObserver;
import Model.UnitsPanelObserver;
import com.irrigation.Messages.MessageFormat.Code;
import com.irrigation.Messages.MessageFormat.Payload;

/**
 * Implementation of login screen GUI controller.
 * @author brune
 */
public class LoginScreenPresenter implements LoginScreenPresenterInterface {
     LoginScreenGUIInterface gui;   
     ServiceManager model;
     /**
     * Creates new controls for login screen component
     * @param gui login screen to be controlled
     * @param model service manager
     */
     public LoginScreenPresenter(LoginScreenGUIInterface gui,ServiceManager model){
         this.gui = gui;
         gui.setPresenter(this);
         this.model = model;
     }
  
    /**
     * Removes the login screen component
     */
    public void removeLoginScreen(){
        gui.removeLoginScreen();
    }
    /**
     * Shows a popup message with text
     * @param text text to be shown
     */
     public void showPopupMessage(String text){
        SwingWorker worker = new SwingWorker<Void, Void>() {
                @Override
                public Void doInBackground() {   
                    gui.setErrorText(text);
                    return null;
                }
         };
        worker.execute(); 
     
    }
     
  
    @Override
    public void onLoginConfirm() {
        Payload answer = null;
        answer = model.checkLogin(gui.getLoginName(), gui.getLoginPassword());
        if(answer.getCode().equals(Code.SUCCESS)){

            ConstantsList.loggedUser = gui.getLoginName();
            ConstantsList.token = answer.getToken();
            System.out.println(ConstantsList.token);

            removeLoginScreen();
            MainFrameGUIInterface view = new MainFrameGUI(820,640);
            MainFramePresenterInterface presenter = new MainFramePresenter(view,model);


            MainPanelGUIInterface panel = new MainPanelGUI();
            MainPanelPresenterInterface panelPresenter = new MainPanelPresenter(panel,model);
            model.getUnitsManager().addObserver((UnitsPanelObserver) panelPresenter);
            model.getSensorsManager().addObserver((SensorsPanelObserver) panelPresenter);


            presenter.initView();
            panelPresenter.initView();
            view.addPanel((Panel) panel);

        //    model.acknowledge(ConstantsList.loggedUser);

        } 
        else{
            System.out.println("Showing error message");
            showPopupMessage("Invalid username or password");
        }    
             
    }

    @Override
    public void onLoginCancel() {
        System.exit(0);
    }
   

    @Override
    public void onRegisterConfirm() {
        RegisterScreenGUIInterface view = new RegisterScreenGUI();
        RegisterScreenPresenterInterface presenter = new RegisterScreenPresenter(view,model);
        presenter.initView();
        this.removeLoginScreen();
    }

    @Override
    public void initView() {
        gui.initView();
    }
}

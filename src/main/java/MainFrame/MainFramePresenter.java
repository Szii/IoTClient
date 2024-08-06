/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFrame;

import Constants.ConstantsList;
import Model.ServiceManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Implementation of main frame GUI controller.
 * @author brune
 */
public class MainFramePresenter implements MainFramePresenterInterface{
     MainFrameGUIInterface view;
    ServiceManager model;
    /**
    * Creates new controls for main window of application
    * @param view window to be controlled 
    * @param model service manager
    */
    public MainFramePresenter(MainFrameGUIInterface view,ServiceManager model){
        this.model = model;
        view.setPresenter(this);
        this.view = view;
    }

    @Override
    public void initView() {
          view.initView();
    }

    @Override
    public void onAddUnitClicked() {
        System.out.println("clicked");
        try {
            String text = JOptionPane.showInputDialog("Type unit ID");
            if(text == null){
                return;
            }
            if(model.checkIfUnitExists(text).equals("false") ){
                JOptionPane.showMessageDialog(null, "Unit does not exist");
                return;
            }
            if(model.checkIfUnitIsRegistered(text).equals("true")){
                JOptionPane.showMessageDialog(null, "Unit is already registered");
                return;
            }
            model.registerUnit(text, ConstantsList.loggedUser);
            model.getUnitsManager().fireNotification("unitsChange", null);
            JOptionPane.showMessageDialog(null, "Unit added and registered");
            
        } catch (InterruptedException ex) {
            Logger.getLogger(MainFramePresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onRefresh() {
       model.getUnitsManager().fireNotification("unitsChange", null);
     //  model.getSensorsManager().fireNotification("sensorsChange", null);
    }
}

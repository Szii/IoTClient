/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFrame;

import Constants.ConstantsList;
import GroupScreen.GroupsGUI;
import GroupScreen.GroupsGUIInterface;
import GroupScreen.GroupsPresenter;
import GroupScreen.GroupsPresenterInterface;
import Model.ServiceManager;
import com.irrigation.Messages.MessageFormat.Code;
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
    public void onAddDeviceClicked() {
        System.out.println("clicked");
        String text = JOptionPane.showInputDialog("Type device ID");
        if(text == null){
            return;
        }
        if(model.registerDevice(text).equals(Code.SUCCESS)){
            model.getUnitsManager().fireNotification("unitsChange", null);
            JOptionPane.showMessageDialog(null, "Device registered");
        }
        else{
            JOptionPane.showMessageDialog(null, "Device does not exist or is already register");
        }
    }

    @Override
    public void onRefresh() {
       model.getUnitsManager().fireNotification("unitsChange", null);
     //  model.getSensorsManager().fireNotification("sensorsChange", null);
    }

    @Override
    public void onGroupsManagementClicked() {
        GroupsGUIInterface gui = new GroupsGUI(view.getFrame());
        GroupsPresenterInterface graphPresenter = new GroupsPresenter(model,gui);
        graphPresenter.initView();
        
    }
}

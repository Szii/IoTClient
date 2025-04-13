/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupScreen;

import Model.SensorsPanelObserver;
import Model.ServiceManager;
import ViewModel.GroupViewModel;
import ViewModel.LiteSensor;
import com.irrigation.Messages.MessageData.Device;
import com.irrigation.Messages.MessageFormat.Code;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author brune
 */
public class GroupsPresenter implements GroupsPresenterInterface, SensorsPanelObserver{
    
    GroupsGUIInterface gui;
    ServiceManager model;
    
    GroupViewModel selectedGroup;
    
    public GroupsPresenter(ServiceManager model,GroupsGUIInterface gui){
        this.gui = gui;
        this.model = model;
        gui.setPresenter(this);
        model.getSensorsManager().addObserver(this);
    }

    @Override
    public void onCreateClicked() {
        System.out.println("clicked");
        String text = JOptionPane.showInputDialog("Type group name");
        if(text == null){
            return;
        }
        if(model.createNewGroup(text).equals(Code.SUCCESS)){
            model.getSensorsManager().fireNotification("sensorsChange");
            JOptionPane.showMessageDialog(null, "Group registered");
        }
        else{
            JOptionPane.showMessageDialog(null, "Group with this name already exist");
        }
    }

    @Override
    public void onRemoveClicked() {
        
        if(model.deleteGroup(selectedGroup.getGroup()).equals(Code.SUCCESS)){
            selectedGroup = (GroupViewModel) gui.getModel().getElementAt(1);
            model.getSensorsManager().fireNotification("sensorsChange");
            JOptionPane.showMessageDialog(null, "Group removed");
        }
        else{
            JOptionPane.showMessageDialog(null, "Error occured when deleting group");
        }
    }

    @Override
    public void onRenameClicked() {
        System.out.println("clicked");
        String text = JOptionPane.showInputDialog("Type group name");
        if(text == null){
            return;
        }
        if(model.changeGroupName(selectedGroup.getGroup(),text).equals(Code.SUCCESS)){
            selectedGroup.setGroup(text);
            model.getSensorsManager().fireNotification("sensorsChange");
            JOptionPane.showMessageDialog(null, "Group name changed");
        }
        else{
            JOptionPane.showMessageDialog(null, "Group with this name already exist");
        }
    }

    @Override
    public void onGroupSelected(GroupViewModel group) {
        selectedGroup = group;
    }

    @Override
    public void initView() {
        ArrayList<GroupViewModel> groups = new ArrayList();
        groups.addAll(model.getGroups());
        gui.setGroupsComboBoxModel(groups);
        selectedGroup = gui.getSelectedUnit();
        gui.initView();
    }

    @Override
    public void onChangeNotification() {
        ArrayList<GroupViewModel> groups = new ArrayList();
        groups.addAll(model.getGroups());
        gui.setGroupsComboBoxModel(groups);
        gui.setSelectedGroup(selectedGroup);
    }

    @Override
    public void onUpdateNotification(ArrayList<Device> registeredSensors, ArrayList<LiteSensor> unregisteredSensors) {
        ArrayList<GroupViewModel> groups = new ArrayList();
        groups.addAll(model.getGroups());
        gui.setGroupsComboBoxModel(groups);
        gui.setSelectedGroup(selectedGroup);

    }

    @Override
    public void onClose() {
        model.getSensorsManager().removeObserver(this);
        gui.getDialog().dispose();
    }
    
    
    
}

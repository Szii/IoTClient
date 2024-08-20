/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupScreen;

import Base.BaseGUIInterface;
import ViewModel.Group;
import ViewModel.UnitObject;
import com.irrigation.Messages.MessageData.Device;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;

/**
 *
 * @author brune
 */
public interface GroupsGUIInterface extends BaseGUIInterface{

    public void setGroupsComboBoxModel(ArrayList<Group> groups);

    public Group getSelectedUnit();
    
    public void clearGroups();
    
    public void setSelectedGroup(Group group);
    
    public DefaultComboBoxModel getModel();
    
    public JDialog getDialog();


    
}

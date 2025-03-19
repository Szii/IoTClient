/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupScreen;

import Base.BaseGUIInterface;
import ViewModel.GroupViewModel;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;

/**
 *
 * @author brune
 */
public interface GroupsGUIInterface extends BaseGUIInterface{

    public void setGroupsComboBoxModel(ArrayList<GroupViewModel> groups);

    public GroupViewModel getSelectedUnit();
    
    public void clearGroups();
    
    public void setSelectedGroup(GroupViewModel group);
    
    public DefaultComboBoxModel getModel();
    
    public JDialog getDialog();


    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import ViewModel.GroupViewModel;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author brune
 */
public class GroupsChooser extends Panel {
    
        
    private DefaultComboBoxModel groups = new DefaultComboBoxModel();
    private JComboBox<GroupViewModel> groupList = new JComboBox();
    
    public GroupsChooser(){
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        Label groupLabel = new Label("Group");
        groupList = new JComboBox();
        groups = new DefaultComboBoxModel();
        groupList.setModel(groups);
        this.add(groupLabel);
        this.add(groupList);
    }
    
    
    public void addItemListener(ItemListener listener){
        groupList.addItemListener(listener);
    }
    
    public void clear(){
        groupList.removeAllItems();
    }
    
    public void setSelectedGroup(GroupViewModel group) {
        groupList.getModel().setSelectedItem(group);
    }
    
    public void setGroups(DefaultComboBoxModel groups) {
        groupList.setModel(groups);
        
    }
    
    public GroupViewModel getGroup(){
        return (GroupViewModel) groupList.getSelectedItem();
    }

    public void addGroup(GroupViewModel group){
        groupList.addItem(group);
    }
}

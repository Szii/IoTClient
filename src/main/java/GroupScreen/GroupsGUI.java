/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupScreen;

import Base.BasePresenterInterface;
import Components.Button;
import Components.Label;
import Components.Panel;
import MainFrame.MainFramePresenter;
import ViewModel.GroupViewModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

/**
 *
 * @author brune
 */
public class GroupsGUI implements GroupsGUIInterface
{
    JFrame parentFrame;
    JDialog frame;
    Panel groupsPanel;
    JComboBox groupsComboBox = new JComboBox();
    Label groupComboBoxLabel = new Label("Group");
    Button createGroupButton = new Button("Create group");
    Button deleteGroup = new Button("Delete group");
    Button renameGroup = new Button ("Rename group");
    
    GroupsPresenterInterface presenter;
    
    public GroupsGUI(JFrame baseFrame){
        this.parentFrame = baseFrame;
    }
    
    private JDialog getFrame(){
        frame = new JDialog(parentFrame,"",true);
        frame.setTitle("Groups management");
        frame.setSize(new Dimension(300,400));
        frame.setLocationRelativeTo(null);
        return frame;
    }
    
    public Panel createMainPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(createGroupsHolderPanel(),BorderLayout.CENTER);
        panel.add(createButtonsHolderPanel(),BorderLayout.EAST);
        return panel;
        
    }
    
    private Panel createGroupsPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(createGroupsComboBoxPanel());
        panel.add(getCreateButton());
        return panel;
    }
    
    private Panel createGroupsHolderPanel(){
         Panel panel = new Panel();
         panel.setBorder(new MatteBorder(2,2,2,1, Color.gray));
         panel.setLayout(new BorderLayout());
         panel.add(createGroupsPanel(),BorderLayout.NORTH);
         return panel;
    }
    
    
    private Panel createGroupsComboBoxPanel(){
        Panel panel = new Panel();
        panel.add(groupsComboBox);
        groupsComboBox.addItemListener((ItemEvent e) -> {
            if(e.getStateChange() == (ItemEvent.SELECTED)){
                presenter.onGroupSelected((GroupViewModel) e.getItem());
                System.out.println("selection changed");
            }

         });
        return panel;
    }
    
    private Panel createGroupButtonsPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(getRenameButton());
        panel.add(getRemoveButton());
        return panel;
    }
    
    private Panel createButtonsHolderPanel(){
        Panel panel = new Panel();
        panel.setBorder(new MatteBorder(2,1,2,2, Color.gray));
        panel.setLayout(new BorderLayout());
        panel.add(createGroupButtonsPanel(),BorderLayout.NORTH);
        return panel;
    }
    
    private Panel getRemoveButton(){
        Panel panel = new Panel();
        panel.add(deleteGroup);
        deleteGroup.addActionListener((ActionEvent e)->{
           presenter.onRemoveClicked();
        });
        return panel;
    }
    
    private Panel getRenameButton(){
        Panel panel = new Panel();
        panel.add(renameGroup);
        renameGroup.addActionListener((ActionEvent e)->{
           presenter.onRenameClicked();
        });
        return panel;
        
    }
    
    private Panel getCreateButton(){
        Panel panel = new Panel();
        panel.add(createGroupButton);
        createGroupButton.addActionListener((ActionEvent e)->{
           presenter.onCreateClicked();
        });
        return panel;
        
    }
    
    @Override
    public void setPresenter(BasePresenterInterface presenter) {
         this.presenter = (GroupsPresenterInterface) presenter;
    }

    @Override
    public void initView() {
        frame = getFrame();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                presenter.onClose();
            }
        });
        frame.add(createMainPanel());
        frame.setResizable(false);
        frame.setVisible(true);
    }

    @Override
    public void updateGUI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setGroupsComboBoxModel(ArrayList<GroupViewModel> groups) {
        groupsComboBox.setModel(new DefaultComboBoxModel(groups.toArray()));
    }

    @Override
    public GroupViewModel getSelectedUnit() {
        return (GroupViewModel) groupsComboBox.getSelectedItem();
    } 

    @Override
    public void clearGroups() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JDialog getDialog() {   
     return frame; 
    }

    @Override
    public void setSelectedGroup(GroupViewModel group) {
       groupsComboBox.getModel().setSelectedItem(group);
    }
    
    @Override
    public DefaultComboBoxModel getModel(){
        return (DefaultComboBoxModel) groupsComboBox.getModel();
    }
}

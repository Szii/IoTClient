/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPanel;

import Base.BasePresenterInterface;
import Components.Label;
import Components.Panel;
import RegisteredSensor.RegisteredSensorGUIInterface;
import ViewModel.GroupViewModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 * Implementation of content panel component of main frame.
 * @author brune
 */
public class MainPanelGUI extends Panel implements MainPanelGUIInterface {
    MainPanelPresenter presenter;
    Panel sensorsPanel;
    Panel unitsPanel;
    Label selectedUnit = new Label("Selected unit: ",SwingConstants.CENTER);
    Label selectedUnitID = new Label("",SwingConstants.CENTER);
    
    private boolean groupChangeListenercanFire;
    
        
    private DefaultComboBoxModel groups = new DefaultComboBoxModel();
    private JComboBox<GroupViewModel> groupList = new JComboBox();
    
    /**
     * Creates new ccntent panel component
     */
    public MainPanelGUI(){
        initView();
    }
    
    private Panel getMainPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(getGroupPanel(),BorderLayout.NORTH);
        panel.add(getSensorsHolder(),BorderLayout.CENTER);
        
        return panel;
    }
    
         private Panel getGroupPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        Label groupLabel = new Label("Group");
        groupList = new JComboBox() {

        /**
         * Do not fire if set by program.
         */
        protected void fireActionEvent() {
            // if the mouse made the selection -> the comboBox has focus
            if(this.hasFocus())
                super.fireActionEvent();
        }
    };
        groups = new DefaultComboBoxModel();
        groupList.setModel(groups);
        groupList.addItemListener(new ItemChangeListener());
        panel.add(groupLabel);
        panel.add(groupList);
        return panel;
    
    }
     
     private Panel getUnitsLabelPanel(){
         Panel panel = new Panel();
         panel.setLayout(new BorderLayout());
         Label label = new Label("Registered units",SwingConstants.CENTER);
         panel.add(label);
         return panel;
     }
     
    private JScrollPane getSensorsHolder(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(getSensorsPanel(),BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setEnabled(false);
        return scrollPane;
    }
    
    private Panel getSensorsPanel(){
        sensorsPanel = new Panel();
        sensorsPanel.setLayout(new BoxLayout(sensorsPanel,BoxLayout.Y_AXIS));
        return sensorsPanel;
    }

    @Override
    public void setPresenter(BasePresenterInterface presenter) {
        this.presenter = (MainPanelPresenter) presenter;
    }

    @Override
    public void initView() {
         this.setBorder(null);
         this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
         this.add(getMainPanel(),BorderLayout.CENTER); 
    }
    
    @Override
    public void addComponent(Component comp) {
        this.add(comp);
    }

    @Override
    public void updateGUI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void clearSensors() {
        sensorsPanel.removeAll();
        sensorsPanel.repaint();
        sensorsPanel.revalidate();
    }

    @Override
    public void addRegisteredSensor(RegisteredSensorGUIInterface sensor) {
        synchronized(this){
        sensorsPanel.add((Panel) sensor);
        sensorsPanel.add(Box.createRigidArea(new Dimension(0, 7)));
        sensorsPanel.repaint();
        sensorsPanel.revalidate();
        }
    }

    @Override
    public void refresh() {
        
        
    }

     @Override
    public void setGroups(DefaultComboBoxModel groups) {
        groupList.setModel(groups);
        
    }
    
    @Override
    public GroupViewModel getGroup(){
        return (GroupViewModel) groupList.getSelectedItem();
    }
    
    
    @Override
    public void clearGroups(){
        groups.removeAllElements();
    }

     @Override
    public void setSelectedGroup(GroupViewModel group) {
        groupList.getModel().setSelectedItem(group);
    }

    @Override
    public void enableGroupListener(boolean enabled){
        groupChangeListenercanFire = enabled;
    }
    @Override
    public void addGroup(GroupViewModel group){
        groupList.addItem(group);
    }
    
        
    class ItemChangeListener implements ItemListener{
    @Override
    public void itemStateChanged(ItemEvent event) {
       if (event.getStateChange() == ItemEvent.SELECTED) {
          Object item = event.getItem();
          if(groupChangeListenercanFire){
               presenter.onGroupClicked();
          }
         
       }
    }       
}


}


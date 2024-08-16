/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegisteredSensor;

import Base.BasePresenterInterface;
import Components.Button;
import Components.Label;
import Components.Panel;
import ViewModel.Group;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

/**
 * Implementation of registered sensor component
 * @author brune
 */
public class RegisteredSensorGUI extends Panel implements RegisteredSensorGUIInterface {
//  sensorsPanelPresenter sensorsPanelPresenter;
    Button registerSensor;
    Label sensorNameLabel = new Label("",SwingConstants.CENTER);
    Label sensorValueLabel;
    Label sensorID = new Label("",SwingConstants.CENTER);
    String sensorName;
  //  SensorPanel_Registered selfRef;
    Button changeNameButton;
    Panel sensorStatusImagePanel = new Panel();
    String nickname;
    Button showGraphButton = new Button("ShowGraph");
    
    private DefaultComboBoxModel groups = new DefaultComboBoxModel();
    private JComboBox<Group> groupList = new JComboBox();
    
    
    Label moistureValueLabel = new Label("-1 %",SwingConstants.CENTER);
    Label timeValueLabel = new Label("3 seconds",SwingConstants.CENTER);
    JTextField text;
    JTextField timeText;
    Button sendThresoldButton = new Button("Send Threshold");
    Button sendTimeButton = new Button("Send Irrigation Time");
    Label moistureThresoldLabel = new Label("-1 %",SwingConstants.CENTER);
    
    boolean groupChangeListenercanFire;
    
    SetNicknameHandler setNicknameListener = new SetNicknameHandler();
    
    RegisteredSensorPresenterInterface presenter;

    @Override
    public void setPresenter(BasePresenterInterface presenter) {
        this.presenter = (RegisteredSensorPresenterInterface) presenter;
    }

    @Override
    public void initView() {
         this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
         this.setLayout(new BorderLayout());
         this.add(getMainPanel());
    }
    
    private Panel getMainPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(null);
        panel.add(getNamePanel());
        panel.add(getValuesPanel());
        panel.add(getSendValuesPanel());
        panel.add(getSendTimePanel());
        panel.add(getGraphPanel());
        panel.add(getBottomPanel());
        return panel;
    }
    
    
    private Panel getNamePanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.add(getSensorNamePanel());
        panel.add(getRenameButtonPanel());
        
        return panel;
    }
    
    private Panel getSensorNamePanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(sensorNameLabel,BorderLayout.CENTER);
        return panel;
    }
    
    private Panel getRenameButtonPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        changeNameButton = new Button("Rename");
        changeNameButton.addActionListener(setNicknameListener);
        panel.add(changeNameButton,BorderLayout.CENTER);
        
        return panel; 
    }
    
    private Panel getValuesPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.add(getThresholdValuePanel());
        panel.add(getMoisturedValuePanel());
        panel.add(getTimeValuePanel());
        return panel;
    }
    
    private Panel getThresholdValuePanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        Label thresholdLabel = new Label("Threshold: ",SwingConstants.CENTER);
        panel.add(thresholdLabel,BorderLayout.WEST);
        panel.add(moistureThresoldLabel,BorderLayout.EAST);
        return panel;
    }
    
    private Panel getMoisturedValuePanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        Label moistureLabel = new Label("Moisture: ", SwingConstants.CENTER);
        panel.add(moistureLabel,BorderLayout.WEST);
        panel.add(moistureValueLabel,BorderLayout.EAST);
        return panel;
    }
    
      private Panel getTimeValuePanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        Label timeLabel = new Label("Irrigation Time: ", SwingConstants.CENTER);
        panel.add(timeLabel,BorderLayout.WEST);
        panel.add(timeValueLabel,BorderLayout.EAST);
        return panel;
    }
    
    private Panel getSendValuesPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        text = new JTextField();
        panel.add(text);
        sendThresoldButton.addActionListener(new SendValueHandler());
        panel.add(sendThresoldButton);
        return panel; 
    }
    
     private Panel getSendTimePanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        timeText = new JTextField();
        panel.add(timeText);
        sendTimeButton.addActionListener(new SendTimeHandler());
        panel.add(sendTimeButton);
        return panel; 
    }
    
    private Panel getBottomPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(getRemovePanel());
        panel.add(getSensorInfoPanelHolder());
        panel.add(getGroupPanel());
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
    
    private Panel getRemovePanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        registerSensor = new Button("Unregister");
        registerSensor.addActionListener((ActionEvent e)->{
            presenter.onRemoveClicked();
        });
        panel.add(registerSensor);
   
        return panel; 
    }
    
       private Panel getGraphPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        showGraphButton.addActionListener((ActionEvent e)->{
            presenter.onShowGraphClicked();
        });
        panel.add(showGraphButton);
   
        return panel; 
    }
    
    private Panel getSensorInfoPanel(){
        Panel panel = new Panel();
        panel.setBorder(null);
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        Label sensorIDLabel = new Label("Sensor ID: ");
        panel.add(sensorIDLabel);
        panel.add(sensorID);
        return panel;
    }
    
    private Panel getSensorInfoPanelHolder(){
        Panel panel = new Panel();
        panel.setBorder(null);
        panel.setLayout(new BorderLayout());
        panel.add(getStatusInfoPanel(),BorderLayout.WEST);
        panel.add(getSensorInfoPanel(),BorderLayout.EAST);
        return panel;
    }
    
    
    private Panel getStatusInfoPanel(){
       Panel panel = new Panel();
       panel.setBorder(null);
       Label IDlabel = new Label("Status: ");
       //panel.setBorder(ConstantsList.classicBorder);
       panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
       panel.add(IDlabel);
       panel.add(sensorStatusImagePanel);
       return panel;
    }
    
     @Override
    public void setGroups(DefaultComboBoxModel groups) {
        groupList.setModel(groups);
        
    }
    
    @Override
    public Group getGroup(){
        return (Group) groupList.getSelectedItem();
    }
    
    
    @Override
    public void clearGroups(){
        groups.removeAllElements();
    }
    
    @Override
    public void enableControls(boolean controlsEnabled){

           sendThresoldButton.setEnabled(controlsEnabled);
    }
    
    public void enableGroupListener(boolean enabled){
        groupChangeListenercanFire = enabled;
    }
    @Override
    public void addGroup(Group group){
        groupList.addItem(group);
    }



    @Override
    public String getThresholdText() {
        return text.getText();
    }
    @Override
    public void setID(String ID){
        sensorID.setText(ID);
    }
    @Override
    public void setNickname(String nickname){
        sensorNameLabel.setText(nickname);
    }
    @Override
    public void setThreshold(String threshold){
        moistureThresoldLabel.setText(threshold + "%");
    }
    @Override
    public void setMoisture(String moisture){
        moistureValueLabel.setText(moisture + "%");
    }

    @Override
    public void updateGUI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setStatus(String status) {
        sensorStatusImagePanel.addImage(status, 15, 15);
    }

    @Override
    public void setIrrigationTime(String time) {
        timeValueLabel.setText(time + " seconds");
    }

    @Override
    public String getTimeText() {
        return timeText.getText();
    }

    @Override
    public void setSelectedGroup(Group group) {
        groupList.getModel().setSelectedItem(group);
    }
     /**
      *  Handler for catching the action event when send value event occurs
      */
    public class SendValueHandler implements ActionListener{
        
        @Override       
        public void actionPerformed(ActionEvent e) {
           presenter.onSendValueClicked();
          
        }
   
    }  
   /**
      *  Handler for catching the action event when change name event occurs
      */
     public class SetNicknameHandler implements ActionListener{
        
        @Override       
        public void actionPerformed(ActionEvent e) {
           presenter.onChangeNameClicked();
        }
        
     }
     /**
      * Handler for catching the action event when send time event occurs
      */
      public class SendTimeHandler implements ActionListener{
        
        @Override       
        public void actionPerformed(ActionEvent e) {
           presenter.onSendTimeClicked();
          
        }
   
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

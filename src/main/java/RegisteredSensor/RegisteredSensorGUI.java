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
import ViewModel.GroupViewModel;
import ViewModel.MeasurementTypeViewModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

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
    Button showHumidityGraphButton = new Button("Show humidity graph");
    Button showTemperatureGraphButton = new Button("Show temperature graph");
    Label optionsLabel = new Label("Options");
    Label setValueslabel = new Label("Set values");
    
    private DefaultComboBoxModel groups = new DefaultComboBoxModel();
    private JComboBox<GroupViewModel> groupList = new JComboBox();
    
    private DefaultComboBoxModel measurementsModel = new DefaultComboBoxModel();
    private JComboBox<MeasurementTypeViewModel> measurementsTypeList = new JComboBox();
    
    
    Label moistureValueLabel = new Label("-1 %",SwingConstants.CENTER);
    Label timeValueLabel = new Label("3 seconds",SwingConstants.CENTER);
    JTextField text;
    JTextField timeText;
    Button sendThresoldButton = new Button("Send Threshold");
    Button sendTimeButton = new Button("Send Irrigation Time");
    Label moistureThresoldLabel = new Label("-1 %",SwingConstants.CENTER);
    
    Font menuFont = new Font ("Comic Sans", Font.BOLD, 15);
    Font valueFont = new Font ("Comic Sans", Font.BOLD, 30);
    
    boolean groupChangeListenercanFire;
    boolean measurementsChangeListenercanFire;
    
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
        panel.add(getTopPanel());
        panel.add(getMiddlePanel());
       // panel.add(getBottomPanel());
        return panel;
    }
    
    private Panel getOptionsLabel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
        optionsLabel.setFont(menuFont);
        panel.add(optionsLabel);

        return panel;
    }
    
    private Panel getSetValuesLabel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        setValueslabel.setFont(menuFont);
        panel.add(setValueslabel,BorderLayout.CENTER);

        return panel;
    }
    
    private Panel getMiddlePanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.add(getSendPanel());
        panel.add(getValuesPanel());
        panel.add(getOptionsPanel()); 
        return panel;
    }
    
    
    private Panel getOptionsPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.add(getOptionsLabel());
        panel.add(getHumidityGraphPanel());
        panel.add(getTemperatureGraphPanel());
        panel.add(getGroupPanel());
        panel.add(getRenameButtonPanel());
        panel.add(getRemovePanel());
        return panel;
    }
    
    private Panel getSendPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.add(getSetValuesLabel());
        panel.add(getSendValuesPanel());
        panel.add(getSendTimePanel());
        return panel;
    }
    
    
    private Panel getNamePanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.add(getSensorNamePanel());
        
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
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.add(getThresholdValuePanel());
        panel.add(getTimeValuePanel());
        panel.add(getMoisturedValuePanel());
        panel.add(getMeasurementTypePanel());
        return panel;
    }
    
    private Panel getMeasurementTypePanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        Label measurementsTypeLabel = new Label("Type ");
        measurementsTypeList = new JComboBox() {

        /**
         * Do not fire if set by program.
         */
        protected void fireActionEvent() {
            // if the mouse made the selection -> the comboBox has focus
            if(this.hasFocus())
                super.fireActionEvent();
        }
    };
        measurementsModel = new DefaultComboBoxModel();
        measurementsTypeList.setModel(measurementsModel);
        measurementsTypeList.addItemListener(new MeasurementsChangeListener());
        panel.add(measurementsTypeLabel);
        panel.add(measurementsTypeList);
        return panel;
            
        
    }
    
    private Panel getThresholdValuePanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        Label thresholdLabel = new Label("Threshold: ",SwingConstants.CENTER);
        thresholdLabel.setFont(menuFont);
        moistureThresoldLabel.setFont(menuFont);
        panel.add(thresholdLabel,BorderLayout.WEST);
        panel.add(moistureThresoldLabel,BorderLayout.EAST);
        return panel;
    }
    
    private Panel getMoisturedValuePanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        moistureValueLabel.setFont(valueFont);
        panel.add(moistureValueLabel,BorderLayout.CENTER);
        return panel;
    }
    
      private Panel getTimeValuePanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        Label timeLabel = new Label("Irrigation Time: ", SwingConstants.CENTER);
        timeLabel.setFont(menuFont);
        timeValueLabel.setFont(menuFont);
        panel.add(timeLabel,BorderLayout.WEST);
        panel.add(timeValueLabel,BorderLayout.EAST);
        return panel;
    }
    
    private Panel getSendValuesPanel(){
        Panel panel = new Panel(new BorderLayout());
        
        
        text = new JTextField();
        text.setPreferredSize(new Dimension(200, 25));
        text.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        
        Panel rowPanel = new Panel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
        rowPanel.setBackground(panel.getBackground());
        
        rowPanel.add(text);
        rowPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        sendThresoldButton.addActionListener(new SendValueHandler());
        rowPanel.add(sendThresoldButton);

        panel.add(rowPanel, BorderLayout.CENTER); 
        
        return panel; 
    }
    
    
    private Panel getSendTimePanel() {
        Panel panel = new Panel(new BorderLayout());

        // Text field setup
        timeText = new JTextField();
        timeText.setPreferredSize(new Dimension(200, 25));
        timeText.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));

        // Row container with horizontal layout
        Panel rowPanel = new Panel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
        rowPanel.setBackground(panel.getBackground());

        rowPanel.add(timeText);
        rowPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        // Button setup
        sendTimeButton.addActionListener(new SendTimeHandler());
        rowPanel.add(sendTimeButton);

        panel.add(rowPanel, BorderLayout.CENTER); 

        return panel;
    }

    
    
    private Panel getTopPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(getSensorInfoPanelHolder());
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
    
       private Panel getHumidityGraphPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        showHumidityGraphButton.addActionListener((ActionEvent e)->{
            presenter.onShowHumidityGraphClicked();
        });
        panel.add(showHumidityGraphButton);
   
        return panel; 
    }
       
    private Panel getTemperatureGraphPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        showTemperatureGraphButton.addActionListener((ActionEvent e)->{
            presenter.onShowTemperatureGraphClicked();
        });
        panel.add(showTemperatureGraphButton);
   
        return panel; 
    }
    
    private Panel getSensorInfoPanel(){
        Panel panel = new Panel();
        panel.setBorder(null);
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        Label sensorIDLabel = new Label("Sensor ID: ");
        sensorIDLabel.setFont(menuFont);
        sensorID.setFont(menuFont);
        panel.add(sensorIDLabel);
        panel.add(sensorID);
        return panel;
    }
    
    private Panel getSensorNicknamePanel(){
        Panel panel = new Panel();
        panel.setBorder(null);
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        Label sensorNicknameInfo = new Label("Nickname: ");
        sensorNicknameInfo.setFont(menuFont);
        sensorNameLabel.setFont(menuFont);
        panel.add(sensorNicknameInfo);
        panel.add(sensorNameLabel);
        return panel;
    }
    
    private Panel getSensorInfoPanelHolder(){
        Panel panel = new Panel();
        panel.setBorder(null);
        panel.setLayout(new BorderLayout());
        panel.add(getSensorInfoPanel(),BorderLayout.EAST);
        panel.add(getSensorNicknamePanel(), BorderLayout.WEST);
        return panel;
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
    public void enableControls(boolean controlsEnabled){

           sendThresoldButton.setEnabled(controlsEnabled);
    }
    
    public void enableGroupListener(boolean enabled){
        groupChangeListenercanFire = enabled;
    }
    @Override
    public void addGroup(GroupViewModel group){
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
        moistureThresoldLabel.setText(threshold + " %");
    }
    @Override
    public void setMoisture(String moisture){
        moistureValueLabel.setText(moisture + " %");
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
    public void setSelectedGroup(GroupViewModel group) {
        groupList.getModel().setSelectedItem(group);
    }

    @Override
    public void setSelectedMeasurementType(MeasurementTypeViewModel measurementTypeViewModel) {
            measurementsTypeList.getModel().setSelectedItem(measurementTypeViewModel);
    }

    @Override
    public void addMeasurementType(MeasurementTypeViewModel measurementTypeViewModel) {
          measurementsTypeList.addItem(measurementTypeViewModel);
    }

    @Override
    public void enableMesurementsTypeListener(boolean enabled) {
           measurementsChangeListenercanFire = enabled;
    }

    @Override
    public MeasurementTypeViewModel getMeasurementType() {
           return (MeasurementTypeViewModel) measurementsTypeList.getSelectedItem();
    }

    @Override
    public void setMeasurementTypes(DefaultComboBoxModel measurementTypes) {
                measurementsTypeList.setModel(measurementTypes);
    }

    @Override
    public void clearMeasurementTypes() {
               measurementsModel.removeAllElements();
    }

    @Override
    public void setTemperature(String temperature) {
             moistureValueLabel.setText(temperature + " C°");
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
    
   class MeasurementsChangeListener implements ItemListener{
    @Override
    public void itemStateChanged(ItemEvent event) {
       if (event.getStateChange() == ItemEvent.SELECTED) {
          Object item = event.getItem();
          if(measurementsChangeListenercanFire){
               presenter.onMeasurementTypeClicked();
          }
         
       }
    }       
}
}

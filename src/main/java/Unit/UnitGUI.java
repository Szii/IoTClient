/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit;

import Base.BasePresenterInterface;
import Components.Button;
import Components.Label;
import Components.Panel;
import Constants.ConstantsList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;



/**
 * Implementation of unit component
 * @author brune
 */
public class UnitGUI extends Panel implements UnitGUIInterface {
     UnitPresenterInterface presenter;
    
    Label unitNameLabel = new Label("",SwingConstants.CENTER);
    Label unitIDLabel = new Label();
    Label unitStatusLabel = new Label();
  
    String nickname;
  
    Button unregisterButton;
    Button showSensors;
    Panel unitStatusImagePanel = new Panel();
    
    UnregisterUnitHandler removeUnitHandler = new UnregisterUnitHandler();
    ShowSensorsHandler showSensorsHandler = new ShowSensorsHandler();
    ChangeNameHandler changeNameListener = new ChangeNameHandler();

    @Override
    public void setPresenter(BasePresenterInterface presenter) {
        this.presenter = (UnitPresenterInterface) presenter;
    }

    @Override
    public void initView() {     
        // this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));   
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.setLayout(new BorderLayout());
        this.add(getNorthPanel(),BorderLayout.NORTH);
        this.add(getButtonsPanel(),BorderLayout.CENTER);
        this.add(getInfoPanel(),BorderLayout.SOUTH);
    }
    
    
    private Panel getNorthPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.add(getNamePanel());
        return panel;
    }
    
    private Panel getNamePanel(){
        Panel panel = new Panel();
        panel.setBorder(null);
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.add(getNameLabelPanel());
        panel.add(getRenameButtonPanel());
        return panel;
    }
    
    private Panel getNameLabelPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(unitNameLabel,BorderLayout.CENTER);
        return panel;
    }
    
    private Panel getRenameButtonPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout()); 
        Button changeNameButton = new Button("Rename");
        changeNameButton.addActionListener(changeNameListener);
        panel.add(changeNameButton,BorderLayout.CENTER);
        return panel;
    }
    
    private Panel getButtonsPanel(){
        Panel panel = new Panel();
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(getControlsButtonsPanel());

        return panel;
    }
    
    private Panel getShowSensorsButton(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        
        showSensors = new Button("Show sensors");
        showSensors.addActionListener(showSensorsHandler); 
        panel.add(showSensors,BorderLayout.CENTER);
        return panel;
    }
    
    private Panel getRemoveButton(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        unregisterButton = new Button("Unregister unit");
        unregisterButton.addActionListener(removeUnitHandler);
        panel.add(unregisterButton,BorderLayout.CENTER);
        
        return panel;
    }
    
    private Panel getControlsButtonsPanel(){
        Panel panel = new Panel();
        panel.setBorder(null);
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.add(getShowSensorsButton());
        panel.add(getRemoveButton());

        return panel;
    }
    
    private Panel getInfoPanel(){
        Panel panel = new Panel();   
       // panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(getIDInfoHolderPanel());
        return panel;
    }
    
    private Panel getIDInfoHolderPanel(){
        Panel panel = new Panel();
        panel.setBorder(null);
        panel.setLayout(new BorderLayout());
        panel.add(getIDInfoPanel(),BorderLayout.EAST);
        panel.add(getStatusInfoPanel(),BorderLayout.WEST);
        return panel;
    }
    
    private Panel getIDInfoPanel(){
       Panel panel = new Panel();
       panel.setBorder(null);
       Label IDlabel = new Label("Unit ID: ");
       //panel.setBorder(ConstantsList.classicBorder);
       panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
       panel.add(IDlabel);
       panel.add(unitIDLabel);
       return panel;
    }
    
    private Panel getStatusInfoPanel(){
       Panel panel = new Panel();
       panel.setBorder(null);
       Label IDlabel = new Label("Status: ");
       //panel.setBorder(ConstantsList.classicBorder);
       panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
       panel.add(IDlabel);
       panel.add(unitStatusImagePanel);
       return panel;
    }
 
    @Override
    public void updateGUI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUnitName(String name) {
       unitNameLabel.setText(name);
    }
    
    @Override
    public void setID(String id){
        unitIDLabel.setText(id);
    }
    
    @Override
    public void setStatus(String status){
        unitStatusImagePanel.addImage(status, 15, 15);
       //  unitStatusLabel.setText(status);
    }
    
    /**
     *  Handler for catching the action event when unregister unit event occurs
    */
    public class UnregisterUnitHandler implements ActionListener{
        
        @Override       
        public void actionPerformed(ActionEvent e) {  
            presenter.onRemoveUnitClicked();
        }
   
    }  
     /**
      *  Handler for catching the action event when show sensors event occurs
      */
      public class ShowSensorsHandler implements ActionListener{    
        @Override       
        public void actionPerformed(ActionEvent e) {          
            presenter.onShowSensorsClicked();      
        }
   
    } 
      /**
       * Handler for catching the action event when change name event occurs
       */
     public class ChangeNameHandler implements ActionListener{
        @Override       
        public void actionPerformed(ActionEvent e) {       
            
            presenter.onChangeNameClicked();
           
        }
   
    } 
}

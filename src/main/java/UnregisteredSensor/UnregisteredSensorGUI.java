/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UnregisteredSensor;

import Base.BasePresenterInterface;
import Components.Button;
import Components.Label;
import Components.Panel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 * Implementation of unregistered sensor component
 * @author brune
 */
public class UnregisteredSensorGUI extends Panel implements UnregisteredSensorGUIInterface {
     UnregisteredSensorPresenterInterface presenter;
    
    Button registerSensorButton;
    Label sensorNameLabel = new Label("",SwingConstants.CENTER);
    RegisterSensorHandler registerSensorHandler;
    

    @Override
    public void setPresenter(BasePresenterInterface presenter) {
        this.presenter = (UnregisteredSensorPresenterInterface) presenter;
    }

    @Override
    public void initView() {
            this.setLayout(new BorderLayout());
            this.add(getMainPanel(),BorderLayout.CENTER);
    }
    
    
    private Panel getMainPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.add(getNamePanel());
        panel.add(getRegisterButtonPanel());
        return panel;
    }
    
    private Panel getNamePanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(sensorNameLabel,BorderLayout.CENTER);
        return panel;
    }
        
    
    private Panel getRegisterButtonPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        registerSensorButton = new Button("Register");
        registerSensorHandler = new RegisterSensorHandler();
        registerSensorButton.addActionListener(registerSensorHandler);
        panel.add(registerSensorButton,BorderLayout.CENTER);
        return panel;
    }

    @Override
    public void updateGUI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void setID(String ID){
        sensorNameLabel.setText(ID);
    }
     /**
      * Handler for catching the action event of clicking on the register sensor component
      */
     public class RegisterSensorHandler implements ActionListener{
     
        @Override       
        public void actionPerformed(ActionEvent e) {
              presenter.onRegisterSensorClicked();
        }
   
    } 
}

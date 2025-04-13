/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatePicker;

import Base.BasePresenterInterface;
import Components.Button;
import Components.DateLabelFormatter;
import Components.Panel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.Properties;
import javax.swing.JDialog;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author brune
 */
public class DatePickerGUI implements DatePickerGUIInterface{
    
    DatePickerPresenterInterface presenter;
    JDialog frame;
    Button confirmButton = new Button("Select");
    JDatePickerImpl datePicker;

    @Override
    public void setPresenter(BasePresenterInterface presenter) {
        this.presenter = (DatePickerPresenterInterface) presenter;
    }
    
    private Panel getMainPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(getDatePickerPanel(),BorderLayout.CENTER);
        panel.add(getConfirmButtonPanel(),BorderLayout.SOUTH);
        return panel;
    }
    
    private JDialog getFrame(){
        frame = new JDialog();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setSize(new Dimension(300,150));
        frame.setTitle("Select a date");
        frame.setLocationRelativeTo(null);
        frame.add(getMainPanel());
        return frame;
        
    }
    
    private Panel getDatePickerPanel(){
        Panel panel = new Panel();
       // panel.setLayout(new BorderLayout());
        
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        
        SqlDateModel model = new SqlDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
        datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
        panel.add(datePicker);
        return panel;
    }
    
    private Panel getConfirmButtonPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        confirmButton.addActionListener((ActionEvent e)->{
            presenter.getConfirmListener().onConfirmClicked();
        });
        panel.add(confirmButton);
        return panel;
    }

    @Override
    public void initView() {
        frame = getFrame();
        frame.setVisible(true);
    }

    @Override
    public void updateGUI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDate() {
        return datePicker.getModel().getValue().toString();
    }
    
    @Override
    public void close() {
        frame.dispose();
    }
    
}

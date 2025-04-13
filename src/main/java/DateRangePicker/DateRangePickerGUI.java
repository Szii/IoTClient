/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DateRangePicker;

import Base.BasePresenterInterface;
import Components.Button;
import Components.DateLabelFormatter;
import Components.Label;
import Components.Panel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.SpinnerDateModel;
import javax.swing.border.BevelBorder;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

/**
 * Implementation of GUI for showing the pick a date option
 * @author brune
 */
public class DateRangePickerGUI implements DateRangePickerGUIInterface {
       
    DateRangePickerPresenterInterface presenter;
    JDialog frame;
    Button confirmButton = new Button("Select");
    JDatePickerImpl firstDatePicker;
    JDatePickerImpl secondDatePicker;
    JSpinner firstTimePicker;
    JSpinner secondTimePicker;
    Label fromLabel = new Label("From");
    Label toLabel = new Label("To");

    @Override
    public void setPresenter(BasePresenterInterface presenter) {
        this.presenter = (DateRangePickerPresenterInterface) presenter;
    }
    
    private Panel getMainPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(getLeftPanel(),BorderLayout.WEST);
        panel.add(getRightPanel(),BorderLayout.EAST);
        panel.add(getConfirmButtonPanel(),BorderLayout.SOUTH);
        return panel;
    }
    
    
    private Panel getLeftPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(getFromPanelLabel());
        panel.add(getFromInputPanel());
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        return panel;
    }
    
    private Panel getRightPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(getToPanelLabel());
        panel.add(getToInputPanel());
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        return panel;
    }
    
    private Panel getFromPanelLabel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(fromLabel);
        return panel;
    }
    
    private Panel getToPanelLabel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(toLabel);
        return panel;
    }
    
    private Panel getFromInputPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(getFirstDatePickerPanel());
        panel.add(getFirstTimePickerPanel());
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        return panel;
    }
    
    private Panel getToInputPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(getSecondDatePickerPanel());
        panel.add(getSecondTimePickerPanel());
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        return panel;
    }
    
    
    

    
    private JDialog getFrame(){
        frame = new JDialog();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setSize(new Dimension(550,250));
        frame.setTitle("Select a range");
        frame.setLocationRelativeTo(null);
        frame.add(getMainPanel());
        return frame;
        
    }
    
    private Panel getFirstDatePickerPanel(){
        Panel panel = new Panel();
        firstDatePicker =  getJDatePanel();
        panel.add(firstDatePicker);
        return panel;
    }
    
    private Panel getFirstTimePickerPanel(){
        Panel panel = new Panel();
        firstTimePicker = getJSpinner();
        panel.add(firstTimePicker);
        return panel;
    }
    
    private Panel getSecondTimePickerPanel(){
        Panel panel = new Panel();
        secondTimePicker = getJSpinner();
        panel.add(secondTimePicker);
        return panel;
    }
    
    private JSpinner getJSpinner(){
        JSpinner spinner = new JSpinner();
        Date date = new Date();
        SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
        spinner.setModel(sm);
        DateEditor de = new DateEditor(spinner, "HH:mm");
        de.getTextField().setEditable( true );
        spinner.setEditor(de);
        return spinner;
       
    }
    
    private JDatePickerImpl getJDatePanel(){
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        SqlDateModel model = new SqlDateModel();
        model.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
        return new JDatePickerImpl(datePanel,new DateLabelFormatter());
    }
    
    
     private Panel getSecondDatePickerPanel(){
        Panel panel = new Panel();
        secondDatePicker = getJDatePanel();
        panel.add(secondDatePicker);
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
    public String getFirstDate() {
        return firstDatePicker.getModel().getValue().toString();
    }

    @Override
    public void close() {
        frame.dispose();
    }

    @Override
    public String getSecondDate() {
        return secondDatePicker.getModel().getValue().toString();
    }

    @Override
    public String getFirstTime() {
        DateEditor de = new DateEditor(firstTimePicker, "HH:mm");
        return de.getTextField().getText();
    }

    @Override
    public String getSecondTime() {
        DateEditor de = new DateEditor(secondTimePicker, "HH:mm");
        return de.getTextField().getText();
    }
    


}

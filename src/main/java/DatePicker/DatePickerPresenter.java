/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatePicker;

import Graph.ConfirmListener;
import Model.ServiceManager;

/**
 * Implementation for controls of picking a date
 * @author brune
 */
public class DatePickerPresenter implements DatePickerPresenterInterface{
    
    
    DatePickerGUIInterface gui;
    ServiceManager model;
    ConfirmListener confirmListener;

    /**
     * Creates new controls for picking a date
     * @param gui GUI to be controlled
     * @param model service manager
     */
    public DatePickerPresenter(DatePickerGUIInterface gui, ServiceManager model) {
        this.gui = gui;
        this.model = model;
        gui.setPresenter(this);
    }

    @Override
    public void initView() {
        gui.initView();
    }

    @Override
    public ConfirmListener getConfirmListener() {
        return confirmListener;
    }

    @Override
    public void setConfirmListener(ConfirmListener confirmListener) {
        this.confirmListener = confirmListener;
    }
}

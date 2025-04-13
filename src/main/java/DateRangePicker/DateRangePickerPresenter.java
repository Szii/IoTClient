/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DateRangePicker;

import Graph.ConfirmListener;
import Model.ServiceManager;

/**
 * Implementation of controls for picking between two date ranges
 * @author brune
 */
public class DateRangePickerPresenter implements DateRangePickerPresenterInterface {

    DateRangePickerGUIInterface gui;
    ServiceManager model;
    ConfirmListener confirmListener;
    /**
    * Creates new controls for picking date between two ranges
    * @param gui GUI to be controlled
    * @param model service manager 
    */
    public DateRangePickerPresenter(DateRangePickerGUIInterface gui, ServiceManager model) {
        this.gui = gui;
        this.model = model;
        gui.setPresenter(this);
    }

    @Override
    public void initView() {
        gui.initView();
    }

    @Override
    public void setConfirmListener(ConfirmListener confirmListener) {
        this.confirmListener = confirmListener;
    }

    @Override
    public ConfirmListener getConfirmListener() {
        return confirmListener;
    }



}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unit;

import Model.ServiceManager;
import ViewModel.UnitObject;
import javax.swing.JOptionPane;

/**
 * Implementation of unit controls
 * @author brune
 */
public class UnitPresenter implements UnitPresenterInterface {
     UnitGUIInterface gui;
    ServiceManager model;
    
    UnitObject unit_ID;
    /**
     * Creates new controls for unit
     * @param gui GUI to be controlled
     * @param model service manager
     * @param unit_ID ID of unit
     */
    public UnitPresenter(UnitGUIInterface gui, ServiceManager model,UnitObject unit_ID){
        this.gui = gui;
        gui.setPresenter(this);
        this.model = model;
        this.unit_ID = unit_ID;
        
    }

    @Override
    public void initView() {
        gui.initView();
    }
    

    @Override
    public void onRemoveUnitClicked() {
        throw new UnsupportedOperationException("Remove unit clicked, not supported");
       // model.removeUnit(unit_ID.getID());
       // model.getUnitsManager().fireNotification("unitsChange", unit_ID);
    }

    @Override
    public void onChangeNameClicked() {
        String text = JOptionPane.showInputDialog("New unit","Write new name for unit");
        if(text == null){
            return;
        }
     //   model.setUnitNickname(unit_ID.getID(), text);
        model.getUnitsManager().fireNotification("unitsChange", unit_ID);
    }

    @Override
    public void onShowSensorsClicked() {
        model.getSensorsManager().fireNotification("sensorsChange",unit_ID);
    }
}

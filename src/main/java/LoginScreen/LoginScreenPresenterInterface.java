/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginScreen;

import Base.BasePresenterInterface;

/**
 * Contains functionalities which can be used by GUI component controlled by implementation of this class
 * @author brune
 */
public interface LoginScreenPresenterInterface extends BasePresenterInterface {
    /**
     * What should happen when login action is invoked by GUI controlled by this controls
     */
    public void onLoginConfirm();
    /**
     * What should happen when cancel login action is invoked by GUI controlled by this controls
    */
    public void onLoginCancel();
    /**
     * What should happen when show register screen action is invoked by GUI controlled by this controls
     */
    public void onRegisterConfirm();
}

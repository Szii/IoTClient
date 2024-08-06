/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegisterScreen;

import Base.BasePresenterInterface;
import LoginScreen.*;

/**
 * Contains functionalities which can be used by GUI component controlled by implementation of this class
 * @author brune
 */
public interface RegisterScreenPresenterInterface extends BasePresenterInterface {
    /**
     * What should happen when registration in canceled
     */
    public void onRegisterCancel();
    /**
     * What should happen when the confirm register action occurs
     */
    public void onCreateAccount();
}

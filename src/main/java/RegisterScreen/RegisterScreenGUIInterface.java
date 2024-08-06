/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegisterScreen;

import Base.BaseGUIInterface;
import Base.BasePresenterInterface;
import LoginScreen.*;

/**
 * Contains functionalities of registered screen GUI component which can be invoked outside of class
 * @author brune
 */
public interface RegisterScreenGUIInterface extends BaseGUIInterface{
    /**
     * Remove registration screen component from the visible area
     */
    public void removeRegisterScreen();
    /**
     * Gets name typed by user
     * @return name typed by user
     */
    public String getRegisterName();
    /**
     * Gets password typed by user
     * @return password typed by user
     */
    public String getRegisterPassword();
    /**
     * Gets confirmation password types by user
     * @return confirmation password types by user
     */
    public String getRegisterConfirmPassword();
    /**
     * Sets error message and show it to the user
     * @param text Text to be shown as message
     */
    public void setErrorMessage(String text);

}

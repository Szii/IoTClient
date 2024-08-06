/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginScreen;

import Base.BaseGUIInterface;
import Base.BasePresenterInterface;

/**
 * Contains functionalities of registered sensor GUI component which can be invoked outside of class
 * @author brune
 */
public interface LoginScreenGUIInterface extends BaseGUIInterface {
    /**
     * Removes itself from the visible area
     */
    public void removeLoginScreen();
    /**
     * Gets a login typed by user
     * @return String of characters as username
     */
    public String getLoginName();
  /**
     * Gets a password typed by user
     * @return String of characters as password
     */
    public String getLoginPassword();
    /**
     * Sets a message to be shown by GUI
     * @param text Text of the message
     */
    public void setErrorText(String text);
    public void setPresenter(BasePresenterInterface presenter);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

/**
 * Data holder class for unit
 * @author brune
 */
public class UnitObject {
    String ID;
    String nickname;

    /**
     * Creates a new unit data holder
     * @param ID ID of unit
     */
    public UnitObject(String ID) {
        this.ID = ID;
    }
    /**
     * ¨Gets a nickname of unit
     * @return nickname of unit, if nickname is null, return ID instead
     */
    public String getNickname() {
        if(nickname != null){
               return nickname;
        }
        else{
            return ID;
        }
     
    }
    /**
     * Sets a nickname for unit data holder
     * @param nickname nickname to be set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    @Override
    public String toString(){
        if(nickname == null || nickname.equals(ID)){
            return ID;
        }
        else{
            return nickname + " (" + ID + ")";
        }
    }

    /**
     * Gets ID of unit
     * @return ID of unit
     */
    public String getID() {
        return ID;
    }
    
    
}

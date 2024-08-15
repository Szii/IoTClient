/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewModel;

/**
 *
 * @author brune
 */
public class Group {
        String group;

    public Group(String group) {
        this.group = group;
    }

    
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    
    
    @Override
    public String toString(){
        return group;
    }
}

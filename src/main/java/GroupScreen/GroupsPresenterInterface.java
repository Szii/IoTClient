/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GroupScreen;

import Base.BasePresenterInterface;
import ViewModel.Group;

/**
 *
 * @author brune
 */
public interface GroupsPresenterInterface extends BasePresenterInterface {
    
    public void onCreateClicked();
    
    public void onRemoveClicked();
    
    public void onRenameClicked();
       
    public void onGroupSelected(Group group);
    
    public void initView();
    
    public void onClose();
    
    
    
}

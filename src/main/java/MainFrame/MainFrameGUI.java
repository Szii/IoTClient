/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainFrame;

import Base.BasePresenterInterface;
import Components.Button;
import Components.Panel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 * Implementation of main frame GUI.
 * @author brune
 */
public class MainFrameGUI extends JFrame implements MainFrameGUIInterface {
     MainFramePresenterInterface presenter;
    JMenuBar menuBar = new JMenuBar();

    
    
    
    //private Panel mainPanel = new Panel();
    private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    
    int xSize;
    int ySize;
    /**
     * Creates new main window of application.
    * @param xSize width of window
    * @param ySize height of window
    */
    public MainFrameGUI(int xSize,int ySize){  
        this.xSize = xSize;
        this.ySize = ySize;
        
    }
    

    @Override
    public void initView(){
        setSize(xSize,ySize); 
       // mainPanel.setLayout(new FlowLayout());
        /*
        JMenuItem add = new JMenuItem("Add Unit");
        JMenu addUnit = new JMenu("Add Unit");
        add.addActionListener((ActionEvent e)->{
            presenter.onAddUnitClicked();
        });
        addUnit.add(add);
        menuBar.add(addUnit);
        menuBar.add(getRefreshPanel());
        */

       // menuBar.add(getBarPanel());
       // this.setJMenuBar(menuBar);
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setLayout(new BorderLayout());
        this.add(getBarHolderPanel(),BorderLayout.NORTH);
        this.setLocationRelativeTo(null);      
        setVisible(true);

    }
    
    private Panel getBarPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.add(getAddUnitPanel());
        panel.add(getRefreshPanel());
        panel.setBorder(null);
        return panel;
    }
    
    private Panel getBarHolderPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        panel.add(getBarPanel(),BorderLayout.EAST);
        panel.setBorder(null);
        return panel;
            
        
    }
    
    
    private Panel getRefreshPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.add(getRefreshButton());
        return panel;
    }
    
    private Button getRefreshButton(){
        Button button = new Button("Refresh");
        button.addActionListener((ActionEvent e)->{
            presenter.onRefresh();
        });
        return button;
    }
    
    private Panel getAddUnitPanel(){
        Panel panel = new Panel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.add(getAddUnitButton());
        return panel;
    }
    
    private Button getAddUnitButton(){
        Button button = new Button("Add Unit");
        button.addActionListener((ActionEvent e)->{
            presenter.onAddUnitClicked();
        });
        return button;
    }
    
    @Override
    public void clear(){
       // mainPanel.removeAll();
        refresh();
    }
    @Override
    public void refresh(){
      //  mainPanel.repaint();
      //  mainPanel.revalidate();
    }
    @Override
    public void addPanel(Panel p){
        
        this.add(p,BorderLayout.CENTER);
    }

    @Override
    public void setPresenter(BasePresenterInterface presenter) {
        this.presenter = (MainFramePresenterInterface) presenter;
    }

    @Override
    public JFrame getFrame() {
       return this;
    }

    @Override
    public void updateGUI() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

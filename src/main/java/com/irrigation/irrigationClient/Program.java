package com.irrigation.irrigationClient;

import ChartDataBuilder.ChartBuilder;
import ChartDataBuilder.DataBuilder;
import LoginScreen.LoginScreenGUI;
import LoginScreen.LoginScreenGUIInterface;
import LoginScreen.LoginScreenPresenter;
import LoginScreen.LoginScreenPresenterInterface;
import Model.InputThread;
import Model.Request;
import Model.Response;
import Model.ServiceManager;
import com.formdev.flatlaf.FlatDarkLaf;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import Model.OutputThread_OOP;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Class server as entry point to the program. Inicialization of shared variables is done here.
 * @author brune
 */
public class Program {
    private ServiceManager manager;
   // final private String hostName = "192.168.0.185";
    final private String hostName = "127.0.0.1";
    public Program(){  
      
        FlatDarkLaf.setup();
   
            
            
/*
            int portNumber1 = 4444;
            int portNumber2 = 4444;
            Socket clientSocket1 = new Socket(hostName, portNumber1);
            Response responder =  new InputThread(clientSocket1);
            Request requester = new OutputThread_OOP(clientSocket1);
            
            responder.start();
        
*/
            
            
            Runnable guiCreator = new Runnable() {
                @Override
                public void run() {
                    ChartBuilder chartBuilder = new ChartBuilder();
                    chartBuilder.setDataBuilder(new DataBuilder());
                    manager = new ServiceManager();
                    manager.setChartBuilder(chartBuilder);
                    LoginScreenGUIInterface gui = new LoginScreenGUI();
                    LoginScreenPresenterInterface presenter =  new LoginScreenPresenter(gui,manager);
                    presenter.initView();  
                }
            };
            SwingUtilities.invokeLater(guiCreator);
            
           
   
       }
    
}

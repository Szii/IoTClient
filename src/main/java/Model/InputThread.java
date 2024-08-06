/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import com.irrigation.Messages.Payload;
import java.io.IOException;
import java.io.ObjectInputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.irrigation.Messages.*;

/**
 * Implementation of response behavior. The thread is initialized by calling start method. The last message which arrived can be retrieved by 
 * calling getMessageAnswerMethod.
 * @author brune
 */
    public class InputThread extends Thread implements  Response{
    //Klientský socket
    Socket clientSocket;
    //Fronta pro ukládání odpovědí od serveru
    LinkedBlockingQueue taskQueue = new LinkedBlockingQueue();
    LinkedBlockingQueue errorQueue = new LinkedBlockingQueue();
    //Inicializace proměnné na ukládání zpráv od serveru
    List<String> messagePacket;
    ObjectInputStream objectInput;

    /**
     * Creates new response implementation. Invoking run method is required to set it running. After that, it collects responses from server. Last response can be got with method getAnswer.
     * @param clientSocket Socket on which class is listening for messages
     * @throws IOException 
     */
    public InputThread(Socket clientSocket) throws IOException{
        this.clientSocket = clientSocket;
    }

    @Override
    public void run(){
        try {
            objectInput = new ObjectInputStream(clientSocket.getInputStream());
            while(true){
                Payload payload = (Payload) objectInput.readObject(); 
                taskQueue.add(payload);
                
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(InputThread.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Connection error");
            System.exit(0);
        }
    }
  
    @Override
    public String getAnswer(MessageType messageType) throws InterruptedException{
        synchronized(this){
            return getMessageAnswer(messageType).getContent().get(0);
        }
    }
    
    @Override
    public boolean checkForError() throws InterruptedException{
        synchronized(this){
            String input = "";
            input = (String)taskQueue.peek();
            if(input != "error"){
                return false;
            }
            else{
                taskQueue.poll();
                return true;
            }
        
        }
    }
    @Override
    public synchronized List<String> getComplexAnswer(MessageType type) throws InterruptedException{
        List<String> message = (List<String>) getMessageAnswer(type).getContent();
        return message;
    }
      
      @Override
      public boolean poolIsEmpty(){
          if(taskQueue.isEmpty()){
              return true;
          }
          else{
              return false;
          }
      }
      @Override
      public void clear(){
          taskQueue.clear();
      }
  
      private Payload getMessageAnswer(MessageType messageType) throws InterruptedException{
        Payload input;
        synchronized(this){
            while((input = (Payload)taskQueue.peek()) == null){
                if(input == null){
                     sleep(1);
                }
                else if(input.getType()!= messageType){
                    sleep(1);
                }
            }
            taskQueue.poll();
            return input;
        }
    }
       
       
    
    
    

}

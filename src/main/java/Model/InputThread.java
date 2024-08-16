/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import com.irrigation.Messages.MessageFormat.MessageType;
import Constants.ConstantsList;
import com.irrigation.Messages.MessageFormat.Payload;
import java.io.IOException;
import java.io.ObjectInputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
                System.out.println("Got payload: " + payload.getContent() + " " + payload.getType() + " " + payload.getCode());
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
            return getPayload(messageType).getContent().get(0);
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
    public synchronized List<String> getComplexAnswer(MessageType type){
        List<String> message = (List<String>) getPayload(type).getContent();
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
  
      @Override
      public Payload<Payload> getPayload(MessageType messageType){
        Payload input;
        synchronized(this){
            while((input = (Payload)taskQueue.peek()) == null){
                if(input == null){
                    try {
                        sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(InputThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(input.getType()!= messageType){
                    try {
                        sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(InputThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            taskQueue.poll();
            System.out.println("got payload: " + input.getType());
            return input;
        }
    }


       
    
    
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.irrigation.Messages.MessageType;
import com.irrigation.Messages.Payload;
import java.util.List;

/**
 * Serves as common service for getting the answers to the request sent to the data source
 * @author brune
 */
public interface Response {
    /**
     * Method gets the last message arrived and interprets its as list of strings
     * @param type Type of message to be waited for
     * @return List of strings representing data carried by response.
     * @throws InterruptedException When service is interrupted, this exception is thrown
     */
    public List<String> getComplexAnswer(MessageType type) throws InterruptedException;
    /**
     * Gets a received response and interprets it as list of strings
     * @param messageType Type of response to be waited for
     * @return list of strings
     * @throws InterruptedException When service is interrupted, this exception is thrown
     */
    public String getAnswer(MessageType messageType) throws InterruptedException;
    
    public Payload getPayload(MessageType messageType)throws InterruptedException;
    /**
     * Check if there are any messages left to be processed
     * @return true if there is at least one message left 
     */
    public boolean poolIsEmpty();
    /**
     * Checks if any message is message signalizing error. Error is sent by data source when it could not process the request properly
     * @return true if error has occurred
     * @throws InterruptedException  When service is interrupted, this exception is thrown
     */
    public boolean checkForError() throws InterruptedException;
    /**
     * Starts the service
     */
    public void start();
    /**
     * Clear all arrived messages
     */
    public void clear();
}

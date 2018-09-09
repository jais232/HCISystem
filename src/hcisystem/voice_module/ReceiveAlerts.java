/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem.voice_module;

import java.io.IOException;

/**
 *
 * @author pra
 */
public class ReceiveAlerts {
    
    
    private static String newString;
    private static String [] con;
    public ReceiveAlerts(String newMessage,String [] c) throws IOException{
    
        newString = newMessage;
        con = c;
        System.out.println("an alert received");
        System.out.println("Just received a new String!! " + newString + "and the string array");
        System.out.println("Gotta process it faster until next string comes!!");
        try {
            StringProcessing sendString = new StringProcessing(newString, con);
        } catch(Exception e) {
        
            System.out.println("String Processing ERROR : " + e.getMessage());
        }
        
    }
    
}

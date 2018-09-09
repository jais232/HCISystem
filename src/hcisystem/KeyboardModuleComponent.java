/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem;

import hcisystem.voice_module.FileControl;
import hcisystem.voice_module.ReceiveAlerts;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pra
 */
public class KeyboardModuleComponent {
 
    
    private static final String OUTSIDE_KEYBOARD_MODE = "$$get_outside_keyboard_mode_$$";

    public void typeIt() {
    
        String data;
        System.out.println("inside typeIt");
        
        try {
            while((data = ServerComponent.br.readLine()) != null) {
                
                
                if(data.equals(OUTSIDE_KEYBOARD_MODE)) {
                
                    System.out.println("getting outside keyboardComponent");
                    break;
                }
                else {
                
                    int l = data.length();
                    Keyboard keyboard = new Keyboard();
                    keyboard.type(data);
                }
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(VoiceModuleComponent.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception e) {
        
            System.out.println(e.getMessage());
        }
    }
}

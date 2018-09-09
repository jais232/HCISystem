/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem;

import hcisystem.gesture_module.KeyboardInterfaceClass;
import hcisystem.voice_module.FileControl;
import hcisystem.voice_module.ReceiveAlerts;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pra
 */
class GameModuleComponent {
 
    
    private static final String OUTSIDE_GAME_MODE = "$$get_outside_game_mode_$$";
    
    public void receiveCommands() {
    
        String data;
        System.out.println("inside GameComponent");
        
        try {
            while((data = ServerComponent.br.readLine()) != null) {
                
                
                if(data.equals(OUTSIDE_GAME_MODE)) {
                
                    System.out.println("getting outside gameComponent");
                    break;
                }
                else {
                
                    KeyboardInterfaceClass kic = new KeyboardInterfaceClass();
                    kic.executeKeyboardCommand(data);
                }
                
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(VoiceModuleComponent.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception e) {
        
            System.out.println(e.getMessage());
        }
        
    }
}

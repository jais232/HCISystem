/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem;

import hcisystem.voice_module.ReceiveAlerts;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import hcisystem.voice_module.FileControl;


/**
 *
 * @author pra
 */
public class VoiceModuleComponent {

    private final ServerComponent serverComp;
    
    private static final String OUTSIDE_VOICE_MODE = "$$get_outside_voice_mode_$$";
    
    
    public VoiceModuleComponent(ServerComponent serverComp) {
    
        this.serverComp = serverComp;
        
    }
    
    public void startVoiceProcessing() throws IOException {
    
        String data;
        System.out.println("inside startVoiceProcessing");
        System.out.println("File control object creation.");
        FileControl fc = new FileControl();
        System.out.println("created file control object and going for open file.");
        String[] c = fc.openFile();
        System.out.println("got the String array.");
        
        try {
            while((data = ServerComponent.br.readLine()) != null) {
                
                
                if(data.equals(OUTSIDE_VOICE_MODE)) {
                
                    System.out.println("getting outside voiceComponent");
                    System.out.println("setting string array to null.");
                    for(int i=0; i<c.length; i++){
                        c[i] = null;
                    }
                    System.out.println("done.");
                    break;
                }
                
                System.out.println("The data is received from phone in voice module --> " + data);
                ServerComponent.voiceMsg.setText(data);

                System.out.println("!!GIVE AN ALERT TO hcisystem.voice_module ABOUT THE NEW DATA!!");
                
                ReceiveAlerts sendAlert = new ReceiveAlerts(data,c);
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(VoiceModuleComponent.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception e) {
        
            System.out.println(e.getMessage());
        }
        
    }
    
}

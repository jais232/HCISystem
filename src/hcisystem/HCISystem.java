/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem;

import hcisystem.voice_module.NewVoiceComm;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author pra
 */

public class HCISystem {

    public static final String IMAGEFOLDERPATH = "C:\\HCISystem\\images"; 
    public static final String GESTUREFOLDERPATH = "C:\\HCISystem\\gesture";
    public static final String VOICEFOLDERPATH = "C:\\HCISystem\\voice";
    
    
    public HCISystem() {
        
        System.out.println("Welcome to HCI system.");
    }
    
    public static void main(String[] args) {
        
        System.out.println("creating directories for storage");
        
        createStorageFolders();
        
        System.out.println("Initiating server component and GUIs");
        
        ServerComponent servComp = new ServerComponent();
        servComp.startServerComponent();
        
    }
    
    private static void createStorageFolders() {
    
        //IMAGE
        File createDirectories = new File(IMAGEFOLDERPATH);
        
        if(!createDirectories.exists()) {
        
            if (createDirectories.mkdirs()) {
                System.out.println("image Directory is created!");
            } else {
                System.out.println("image Failed to create directory! need to look into the problem!!!");
            }
            
        }
        else {
        
            System.out.println("image Directories exits already.");
        }
        
        //GESTURE
        File createDirectorie = new File(GESTUREFOLDERPATH);
        
        if(!createDirectorie.exists()) {
        
            if (createDirectorie.mkdir()) {
                
                System.out.println("gesture Directory is created!");
            } else {
                System.out.println("gesture Failed to create directory! need to look into the problem!!!");
            }
            
        }
        else {
        
            System.out.println("gesture Directorie exits already.");
        }
        
        File commFile = new File(GESTUREFOLDERPATH + "\\name_comm.act");
        
        if(!commFile.exists()) {
        
            try {
                commFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(HCISystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
        
            System.out.println("geature command file exists already.");
        }
        
        //VOICE
        createDirectorie = new File(VOICEFOLDERPATH);
        
        if(!createDirectorie.exists()) {
        
            if (createDirectorie.mkdir()) {
                
                System.out.println("voice Directory is created!");
            } else {
                System.out.println("voice Failed to create directory! need to look into the problem!!!");
            }
            
        }
        else {
        
            System.out.println("voice Directorie exits already.");
        }
        
        File voiFile = new File(VOICEFOLDERPATH + "\\database.dat");
        
        if(!voiFile.exists()) {
        
            try {
                voiFile.createNewFile();
                NewVoiceComm nvc = new NewVoiceComm();
                nvc.addVoiceCommands();
            } catch (IOException ex) {
                Logger.getLogger(HCISystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
        
            System.out.println("voice command file exists already.");
        }
        
        
        
        
    }
}

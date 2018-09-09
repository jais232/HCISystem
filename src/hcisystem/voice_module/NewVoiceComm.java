/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem.voice_module;

import hcisystem.HCISystem;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author pra
 */
public class NewVoiceComm {
    
    public void addVoiceCommands() {
    
        FileWriter fw = null;
        try {
            
            File voiFile = new File(HCISystem.VOICEFOLDERPATH + "//database.dat");
            fw = new FileWriter(voiFile);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write("open notepad");
            bw.newLine();
            bw.write("start notepad");
            bw.newLine();
            
            bw.write("create notepad file");
            bw.newLine();
            bw.write("start notepad");
            bw.newLine();
            
            bw.write("open explorer");
            bw.newLine();
            bw.write("start explorer");
            bw.newLine();
            
            bw.write("open notepad++");
            bw.newLine();
            bw.write("start notepad++");
            bw.newLine();
            
            bw.write("open chrome");
            bw.newLine();
            bw.write("start chrome");
            bw.newLine();
            
            bw.write("close notepad");
            bw.newLine();
            bw.write("taskkill/im notepad.exe");
            bw.newLine();
            
            bw.write("open internet explorer");
            bw.newLine();
            bw.write("start iexplore.exe");
            bw.newLine();
            
            bw.write("close internet explorer");
            bw.newLine();
            bw.write("taskkill/im iexplore.exe");
            bw.newLine();
            
            bw.write("open cmd");
            bw.newLine();
            bw.write("taskkill/im cmd.exe");
            bw.newLine();
            
            bw.write("close cmd");
            bw.newLine();
            bw.write("taskkill/im cmd.exe");
            bw.newLine();
            
            
            bw.write("open task manager");
            bw.newLine();
            bw.write("taskmgr.exe");
            bw.newLine();
            
            bw.write("close task manager");
            bw.newLine();
            bw.write("taskkill/im taskmgr.exe");
            bw.newLine();
            
            bw.write("open word");
            bw.newLine();
            bw.write("start winword.exe");
            bw.newLine();
            
            bw.write("close word");
            bw.newLine();
            bw.write("taskkill/im word.exe");
            bw.newLine();
            
            bw.write("open powerpoint");
            bw.newLine();
            bw.write("start powerpnt.exe");
            bw.newLine();
            
            bw.write("close powerpoint");
            bw.newLine();
            bw.write("taskkill/im powerpnt.exe");
            bw.newLine();
            
            bw.write("open excel");
            bw.newLine();
            bw.write("start excel.exe");
            bw.newLine();
            
            bw.write("close excel");
            bw.newLine();
            bw.write("taskkill/im excel.exe");
            bw.newLine();
            
            bw.write("open paint");
            bw.newLine();
            bw.write("start mspaint.exe");
            bw.newLine();
            
            bw.write("close paint");
            bw.newLine();
            bw.write("taskkill/im mspaint.exe");
            bw.newLine();
            
            bw.write("open sound mixer");
            bw.newLine();
            bw.write("sndvol.exe -f");
            bw.newLine();
      
            bw.write("open recycle bin");
            bw.newLine();
            bw.write("start shell:RecycleBinFolder");
            bw.newLine();
            
            bw.write("empty recycle bin");
            bw.newLine();
            bw.write("rd /s/q %systemdrive%\\$Recycle.bin");
            bw.newLine();

            bw.write("show system properties");
            bw.newLine();
            bw.write("sysdm.cpl");
            bw.newLine();

            bw.write("show computer properties");
            bw.newLine();
            bw.write("start control system");
            bw.newLine();
            
            bw.write("open control panel");
            bw.newLine();
            bw.write("start control.exe");
            bw.newLine();
            
            bw.write("show display properties");
            bw.newLine();
            bw.write("start Desk.cpl");
            bw.newLine();
            
            bw.write("show mouse properties");
            bw.newLine();
            bw.write("start Main.cpl");
            bw.newLine();
            
            bw.write("shutdown computer");
            bw.newLine();
            bw.write("shutdown /s");
            bw.newLine();
            
            bw.write("restart computer");
            bw.newLine();
            bw.write("shutdown /r");
            bw.newLine();
            
            bw.write("log off");
            bw.newLine();
            bw.write("shutdown /l");
            bw.newLine();
            
            /*ADD NEW COMMANDS OVER HERE.
            bw.write("");
            bw.newLine();
            bw.write("");
            bw.newLine();
            */
            
            bw.close();
            fw.close();
        } catch (Exception ex) {
            
            System.out.println(ex.getMessage());
        }
        
    }
}
